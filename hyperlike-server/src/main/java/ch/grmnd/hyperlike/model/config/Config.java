package ch.grmnd.hyperlike.model.config;

import ch.grmnd.hyperlike.model.Bounds;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

/**
 * Representation of a survey configuration with
 * title, geographic bounds, input definitions, analysis method
 * and display parameters. Deserialized during startup from file <code>config.json</code>.
 */
public class Config implements Serializable {

    private final String title;
    private final Bounds bounds;
    private final Set<InputConfig> input;
    private final AnalysisConfig analysis;
    private final DisplayConfig display;

    private Config() {
        title = null;
        bounds = null;
        analysis = null;
        input = null;
        display = null;
    }

    public String getTitle() {
        return title;
    }

    public Bounds getBounds() {
        return bounds;
    }

    public Set<InputConfig> getInput() {
        return input;
    }

    public AnalysisConfig getAnalysis() {
        return analysis;
    }

    public DisplayConfig getDisplay() {
        return display;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Config config = (Config) o;

        if (title != null ? !title.equals(config.title) : config.title != null) return false;
        if (bounds != null ? !bounds.equals(config.bounds) : config.bounds != null) return false;
        if (analysis != null ? !analysis.equals(config.analysis) : config.analysis != null)
            return false;
        if (input != null ? !input.equals(config.input) : config.input != null) return false;
        return !(display != null ? !display.equals(config.display) : config.display != null);

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (bounds != null ? bounds.hashCode() : 0);
        result = 31 * result + (analysis != null ? analysis.hashCode() : 0);
        result = 31 * result + (input != null ? input.hashCode() : 0);
        result = 31 * result + (display != null ? display.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Config{" +
                "title='" + title + '\'' +
                ", bounds=" + bounds +
                ", analysis=" + analysis +
                ", input=" + input +
                ", display=" + display +
                '}';
    }
}
