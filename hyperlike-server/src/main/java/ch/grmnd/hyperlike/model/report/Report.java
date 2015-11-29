package ch.grmnd.hyperlike.model.report;

import ch.grmnd.hyperlike.model.analysis.AnalysedValue;

import java.io.Serializable;
import java.util.Set;

/**
 * Representation of an analysis report, to be serialized an transmitted to client.
 */
public class Report implements Serializable {

    private final AnalysedValue meta;
    private final Set<AnalysedValue> values;

    private Report() {
        meta = null;
        values = null;
    }

    public Report(AnalysedValue meta, Set<AnalysedValue> values) {
        this.meta = meta;
        this.values = values;
    }

    public AnalysedValue getMeta() {
        return meta;
    }

    public Set<AnalysedValue> getValues() {
        return values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Report report = (Report) o;

        if (getMeta() != null ? !getMeta().equals(report.getMeta()) : report.getMeta() != null) return false;
        return !(getValues() != null ? !getValues().equals(report.getValues()) : report.getValues() != null);

    }

    @Override
    public int hashCode() {
        int result = getMeta() != null ? getMeta().hashCode() : 0;
        result = 31 * result + (getValues() != null ? getValues().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Report{" +
                "meta=" + meta +
                ", values=" + values +
                '}';
    }
}
