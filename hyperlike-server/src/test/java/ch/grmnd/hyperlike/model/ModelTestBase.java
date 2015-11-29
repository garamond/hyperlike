package ch.grmnd.hyperlike.model;

import ch.grmnd.hyperlike.HyperlikeTestCfg;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;

@SpringApplicationConfiguration(classes = HyperlikeTestCfg.class)
public class ModelTestBase {

    @Autowired
    protected ObjectMapper objectMapper;

}
