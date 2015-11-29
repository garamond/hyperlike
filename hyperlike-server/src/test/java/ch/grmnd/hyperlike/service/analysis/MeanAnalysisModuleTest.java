package ch.grmnd.hyperlike.service.analysis;

import ch.grmnd.hyperlike.HyperlikeTestCfg;
import ch.grmnd.hyperlike.model.LatLon;
import ch.grmnd.hyperlike.model.collect.CollectionInput;
import ch.grmnd.hyperlike.service.config.ConfigService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringApplicationConfiguration(classes = HyperlikeTestCfg.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class MeanAnalysisModuleTest {

    @Autowired
    ConfigService configService;
    
    @Test
    public void testAnalyze() throws Exception {
        MeanAnalysisModule weightedMeanAnalysisModule = new MeanAnalysisModule();
        Map<String, Double> values = new HashMap<>();
        values.put("pop", 33.0);
        values.put("vibe", 43.0);
        values.put("temp", 53.0);
        CollectionInput collectionInput = new CollectionInput(new LatLon(0.0,0.0), new Date(0), values);
        Double res = weightedMeanAnalysisModule.analyze(collectionInput, configService.getConfig());
        Assert.assertEquals(new Double(43.0), res);
    }
}