package ch.grmnd.hyperlike.model.config;

import ch.grmnd.hyperlike.model.ModelTestBase;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.nio.file.Path;
import java.nio.file.Paths;

@RunWith(SpringJUnit4ClassRunner.class)
public class ConfigTest extends ModelTestBase {

    @Test
    public void testSerializationEquals() throws Exception {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        Path cfgFile = Paths.get("config.json");
        Config config = objectMapper.readValue(cfgFile.toFile(), Config.class);
        String jsonStr = objectMapper.writeValueAsString(config);
        Config config2 = objectMapper.readValue(jsonStr, Config.class);
        Assert.assertEquals(config, config2);
    }

}