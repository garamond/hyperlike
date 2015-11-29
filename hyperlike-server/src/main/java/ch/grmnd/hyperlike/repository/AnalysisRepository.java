package ch.grmnd.hyperlike.repository;

import ch.grmnd.hyperlike.model.analysis.AnalysedValue;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Set;

/**
 * Interface for accessing analysis results for inclusion in a report.
 */
public interface AnalysisRepository extends MongoRepository<AnalysedValue, String> {

    AnalysedValue findOneByName(String name);

    Set<AnalysedValue> findAllByNameNot(String name);

}
