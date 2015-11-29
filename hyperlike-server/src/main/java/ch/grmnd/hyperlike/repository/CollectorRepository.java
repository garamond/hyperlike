package ch.grmnd.hyperlike.repository;

import ch.grmnd.hyperlike.model.collect.CollectionInput;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Interface for accessing the collector's repository for saving new survey input.
 */
public interface CollectorRepository extends MongoRepository<CollectionInput, String> {

}
