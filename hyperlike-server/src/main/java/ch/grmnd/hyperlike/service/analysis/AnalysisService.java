package ch.grmnd.hyperlike.service.analysis;

import ch.grmnd.hyperlike.model.analysis.AnalysedValue;
import ch.grmnd.hyperlike.model.collect.CollectionInput;
import ch.grmnd.hyperlike.service.config.Adapter;

import java.util.Set;

/**
 * Service performing analysis of survey input using the configured AnalysisModule
 */
public interface AnalysisService extends Adapter {

    void process(CollectionInput collectionInput);

    /**
     * Retrieve all analysed values of the survey.
     *
     * @return analyzed values
     */
    Set<AnalysedValue> getValues();

    /**
     * Returns the values of the analysis of the entire configured region.
     *
     * @return meta value of survey
     */
    AnalysedValue getMeta();

}
