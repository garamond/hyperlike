package ch.grmnd.hyperlike.rest;

import ch.grmnd.hyperlike.model.config.Config;
import ch.grmnd.hyperlike.service.config.Adapter;
import ch.grmnd.hyperlike.service.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST interface for providing config information to client application instances and
 * survey providers.
 */
@RestController
public class ConfigController implements Adapter {

    public static final String ENDPOINT = "/api/config";

    @Autowired
    ConfigService configService;

    @RequestMapping(value = ConfigController.ENDPOINT, method = RequestMethod.GET)
    Config get() {
        return configService.getConfig();
    }

    @Override
    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }

}
