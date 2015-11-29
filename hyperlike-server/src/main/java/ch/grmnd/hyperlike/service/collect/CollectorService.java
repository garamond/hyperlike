package ch.grmnd.hyperlike.service.collect;

import ch.grmnd.hyperlike.model.collect.CollectionInput;

/**
 * Service for the colelction of new survey input values.
 */
public interface CollectorService {

    void save(CollectionInput collectionInput);
    
}
