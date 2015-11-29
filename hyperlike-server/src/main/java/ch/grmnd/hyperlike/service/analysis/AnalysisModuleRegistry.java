package ch.grmnd.hyperlike.service.analysis;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Container for analysis methods to select during startup.
 */
@Component
public class AnalysisModuleRegistry {

    private final Map<String, AnalysisModule> instances = new HashMap<>();

    public AnalysisModuleRegistry() {
        instances.put("mean", new MeanAnalysisModule());
    }

    public AnalysisModule findModule(String name) {
        AnalysisModule module = instances.get(name);
        if (module != null) {
            return module;
        } else {
            throw new IllegalArgumentException("No such module: "+name);
        }
    }

}
