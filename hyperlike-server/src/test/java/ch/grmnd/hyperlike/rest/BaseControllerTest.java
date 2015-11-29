package ch.grmnd.hyperlike.rest;

import ch.grmnd.hyperlike.HyperlikeTestCfg;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@SpringApplicationConfiguration(classes = HyperlikeTestCfg.class)
@WebIntegrationTest("server.port=0")
public abstract class BaseControllerTest extends TestCase {

    @Value("${local.server.port}")
    protected int serverPort;

    String buildUrl(String endpoint) {
        return String.format("http://localhost:%s%s", serverPort, endpoint);
    }

}