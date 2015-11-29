package ch.grmnd.hyperlike.service.collect;

import ch.grmnd.hyperlike.model.collect.CollectionInput;
import ch.grmnd.hyperlike.repository.CollectorRepository;
import ch.grmnd.hyperlike.service.analysis.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Default implementation of CollectorService saving new survey input values
 * to database, as well as handing them over to the analysis service.
 */
@Component
final class CollectorServiceImpl implements CollectorService {

    @Autowired
    CollectorRepository collectorRepository;

    @Autowired
    AnalysisService analysisService;

    @Override
    public void save(CollectionInput collectionInput) {
        collectorRepository.insert(collectionInput);
        analysisService.process(collectionInput);
    }

}
