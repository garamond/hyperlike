package ch.grmnd.hyperlike.service.config;

import ch.grmnd.hyperlike.model.config.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Default implementation of ConfigService reading the survey configuration
 * from a file <code>config.json</code> in the current directory.
 */
@Component
public class ConfigServiceImpl implements ConfigService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigServiceImpl.class);

    @Autowired
    ObjectMapper objectMapper;

    private Config config;

    @PostConstruct
    public void init() throws IOException {
        Path configFile = Paths.get("config.json");
        this.config = objectMapper.readValue(configFile.toFile(), Config.class);
        LOGGER.info(config.toString());
    }

    @Override
    public Config getConfig() {
        return config;
    }

}
