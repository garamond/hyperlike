package ch.grmnd.hyperlike.service.analysis;

import ch.grmnd.hyperlike.model.Bounds;
import ch.grmnd.hyperlike.model.LatLon;
import ch.grmnd.hyperlike.model.Rectangle;
import ch.grmnd.hyperlike.model.analysis.AnalysedValue;
import ch.grmnd.hyperlike.model.collect.CollectionInput;
import ch.grmnd.hyperlike.model.config.Config;
import ch.grmnd.hyperlike.repository.AnalysisRepository;
import ch.grmnd.hyperlike.service.config.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

/**
 * Default implementation of AnalysisService
 */
@Component
public class AnalysisServiceImpl implements AnalysisService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnalysisServiceImpl.class);

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    AnalysisRepository analysisRepository;

    @Autowired
    AnalysisModuleRegistry moduleRegistry;

    @Autowired
    ConfigService configService;

    AnalysisModule analysisModule;

    @PostConstruct
    void init() {
        analysisModule = moduleRegistry.findModule(configService.getConfig().getAnalysis().getMethod());
        if (mongoTemplate.collectionExists(AnalysedValue.class)){
            LOGGER.debug("Analysis database already exists.");
        } else {
            Config config = configService.getConfig();
            Double y = config.getBounds().getNorthEast().getLat() - configService.getConfig().getBounds().getSouthWest().getLat();
            Double x = config.getBounds().getNorthEast().getLon() - configService.getConfig().getBounds().getSouthWest().getLon();
            Double dy = y / config.getAnalysis().getGrid();
            Double dx = x / config.getAnalysis().getGrid();
            Bounds configBounds = config.getBounds();
            int idx = 0;
            for (int i=0; i<config.getAnalysis().getGrid(); i++) {
                for (int j=0; j<config.getAnalysis().getGrid(); j++) {
                    Bounds bounds = new Bounds(new LatLon(configBounds.getSouthWest().getLat()+(i*dy), configBounds.getSouthWest().getLon()+(j*dx)),
                            new LatLon(configBounds.getSouthWest().getLat()+((i+1)*dy), configBounds.getSouthWest().getLon()+((j+1)*dx)));
                    AnalysedValue initVal = new AnalysedValue(String.format("segment%s", idx+1), null, null, null, 0L, null, new Rectangle(bounds.getSouthWest(), bounds.getNorthEast()));
                    //AnalysedValue initVal = new AnalysedValue(String.format("segment%s", idx+1), 0d, new Double(idx+1), 0d, 1000L, new Date(), new Rectangle(bounds.getSouthWest(), bounds.getNorthEast()));
                    analysisRepository.insert(initVal);
                    idx += 1;
                }
            }
            analysisRepository.insert(new AnalysedValue("meta", null, null, null, 0L, null, new Rectangle(config.getBounds().getSouthWest(), config.getBounds().getNorthEast())));
            //analysisRepository.insert(new AnalysedValue("meta", 0d, new Double(idx+1), 36d, 9000L, new Date(), new Rectangle(config.getBounds().getSouthWest(), configBounds.getNorthEast())));
            LOGGER.info("Initialized database");
        }

    }

    @Override
    public void process(CollectionInput collectionInput) {
        BasicQuery intersectQuery = new BasicQuery(String.format("{area: {$geoIntersects: {$geometry: {type: \"Point\", coordinates: [%s, %s]}} }}", collectionInput.getPos().getLon(), collectionInput.getPos().getLat()));
        Double value = analysisModule.analyze(collectionInput, configService.getConfig());
        List<AnalysedValue> list = mongoTemplate.find(intersectQuery, AnalysedValue.class);
        for (AnalysedValue existing: list) {
            AnalysedValue update = new AnalysedValue(
                    existing.getId(),
                    existing.getName(),
                    (existing.getMin()==null || existing.getMin() > value) ? value : existing.getMin(),
                    (existing.getMax()==null || existing.getMax() < value) ? value : existing.getMax(),
                    existing.getSum()==null ? value : existing.getSum()+value,
                    existing.getCount()+1,
                    collectionInput.getTstamp(),
                    existing.getArea()
            );
            update.addComputedValue("mean", update.getSum() / update.getCount());
            analysisRepository.save(update);
        }
    }

    @Override
    public Set<AnalysedValue> getValues() {
        return analysisRepository.findAllByNameNot("meta");
    }

    @Override
    public AnalysedValue getMeta() {
        return analysisRepository.findOneByName("meta");
    }

    @Override
    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }
}
