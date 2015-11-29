package ch.grmnd.hyperlike.rest;

import ch.grmnd.hyperlike.model.LatLon;
import ch.grmnd.hyperlike.model.collect.CollectionInput;
import ch.grmnd.hyperlike.repository.CollectorRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;

@RunWith(SpringJUnit4ClassRunner.class)
public class CollectorControllerTest extends BaseControllerTest {

    @Autowired
    CollectorRepository collectorRepository;

    @Test
    public void testPost() throws Exception {

        RestTemplate restTemplate = new RestTemplate();

        HashMap<String, Double> values = new HashMap<>();
        values.put("pop", 10.5);
        values.put("vibe", 5.0);
        values.put("temp", 21.3);
        CollectionInput collectionInput = new CollectionInput(new LatLon(47.3866933, 8.514570299999999), new Date(0), values);

        Assert.assertEquals(0, collectorRepository.findAll().size());
        restTemplate.postForLocation(buildUrl(CollectorController.ENDPOINT), collectionInput);
        Assert.assertEquals(1, collectorRepository.findAll().size());

    }
}