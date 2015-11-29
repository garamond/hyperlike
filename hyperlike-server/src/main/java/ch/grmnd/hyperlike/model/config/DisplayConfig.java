package ch.grmnd.hyperlike.model.config;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Configuration of the survey result display. Parameters are
 * the metric to use for result selection,
 * the quorum (minimum number of participating users to make segments visible)
 * and the list of colors to colorize the result map.
 */
public class DisplayConfig implements Serializable {

    private final String metric;
    private final Integer quorum;
    private final List<String> colors;

    private DisplayConfig() {
        metric = null;
        quorum = null;
        colors = null;
    }

    public String getMetric() {
        return metric;
    }

    public Integer getQuorum() {
        return quorum;
    }

    public List<String> getColors() {
        return colors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DisplayConfig that = (DisplayConfig) o;

        if (metric != null ? !metric.equals(that.metric) : that.metric != null) return false;
        if (quorum != null ? !quorum.equals(that.quorum) : that.quorum != null) return false;
        return !(colors != null ? !colors.equals(that.colors) : that.colors != null);

    }

    @Override
    public int hashCode() {
        int result = metric != null ? metric.hashCode() : 0;
        result = 31 * result + (quorum != null ? quorum.hashCode() : 0);
        result = 31 * result + (colors != null ? colors.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DisplayConfig{" +
                "metric='" + metric + '\'' +
                ", quorum=" + quorum +
                ", colors=" + colors +
                '}';
    }
}
