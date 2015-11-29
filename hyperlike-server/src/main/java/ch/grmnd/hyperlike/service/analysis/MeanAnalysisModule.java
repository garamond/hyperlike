package ch.grmnd.hyperlike.service.analysis;

import ch.grmnd.hyperlike.model.collect.CollectionInput;
import ch.grmnd.hyperlike.model.config.Config;
import ch.grmnd.hyperlike.model.config.InputConfig;

/**
 * Implementation of analysis method using mean of all values in a survey input tuple
 */
final class MeanAnalysisModule implements AnalysisModule {

    @Override
    public Double analyze(CollectionInput input, Config config) {
        Double sum = 0d;
        for (InputConfig ic : config.getInput()) {
            sum += input.getValues().get(ic.getId());
        }
        return sum / input.getValues().size();
    }

}