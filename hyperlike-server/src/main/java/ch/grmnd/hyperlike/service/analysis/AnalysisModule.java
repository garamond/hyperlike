package ch.grmnd.hyperlike.service.analysis;

import ch.grmnd.hyperlike.model.collect.CollectionInput;
import ch.grmnd.hyperlike.model.config.Config;

import java.util.Map;

/**
 * Method of analysis of survey results.
 */
interface AnalysisModule {

    /**
     * Performs calculation and returns analysis result.
     *
     * @param input to survey
     * @param config of survey
     * @return computed analysis value
     */
    Double analyze(CollectionInput input, Config config);

}
