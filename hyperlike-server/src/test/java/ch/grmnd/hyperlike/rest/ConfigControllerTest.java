package ch.grmnd.hyperlike.rest;

import ch.grmnd.hyperlike.model.config.Config;
import ch.grmnd.hyperlike.service.config.ConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
public class ConfigControllerTest  extends BaseControllerTest{

    @Autowired
    ConfigService configService;

    @Test
    public void testGetConfig() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        Config config = restTemplate.getForEntity(buildUrl(ConfigController.ENDPOINT), Config.class).getBody();
        assertEquals(configService.getConfig(), config);
    }
}