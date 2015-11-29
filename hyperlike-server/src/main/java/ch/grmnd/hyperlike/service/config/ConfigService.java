package ch.grmnd.hyperlike.service.config;

import ch.grmnd.hyperlike.model.config.Config;

/**
 * Service providing access to the current survey configuration.
 */
public interface ConfigService {

    Config getConfig();

}
