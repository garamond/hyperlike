package ch.grmnd.hyperlike.model.config;

import java.io.Serializable;

/**
 * Configuration of the analysis process; consists of the size of the analysis grid and
 * the name of the analysis method (will be resolved by AnalysisRegistry during startup)
 */
public class AnalysisConfig implements Serializable {

    private final Integer grid;
    private final String method;

    private AnalysisConfig() {
        grid = null;
        method = null;
    }

    public Integer getGrid() {
        return grid;
    }

    public String getMethod() {
        return method;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnalysisConfig that = (AnalysisConfig) o;

        if (grid != null ? !grid.equals(that.grid) : that.grid != null) return false;
        return !(method != null ? !method.equals(that.method) : that.method != null);

    }

    @Override
    public int hashCode() {
        int result = grid != null ? grid.hashCode() : 0;
        result = 31 * result + (method != null ? method.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AnalysisConfig{" +
                "grid=" + grid +
                ", method='" + method + '\'' +
                '}';
    }
}
