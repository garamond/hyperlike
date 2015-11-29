package ch.grmnd.hyperlike.model.analysis;

import ch.grmnd.hyperlike.model.Rectangle;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Representation of the result of the analysis process, to be part of a Report.
 */
@Document
public class AnalysedValue implements Serializable {

    @Id
    @JsonIgnore
    private String id;

    private final String name;
    private final Double min;
    private final Double max;
    private final Double sum;
    private final Long count;
    private final Map<String, Double> computed = new HashMap<>();
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private final Date last;
    private final Rectangle area;

    AnalysedValue() {
        name = null;
        min = null;
        max = null;
        sum = null;
        count = null;
        last = null;
        area = null;
    }

    public AnalysedValue(String id, String name, Double min, Double max, Double sum, Long count, Date last, Rectangle area) {
        this.id = id;
        this.name = name;
        this.min = min;
        this.max = max;
        this.sum = sum;
        this.count = count;
        this.last = last;
        this.area = area;
    }

    public AnalysedValue(String name, Double min, Double max, Double sum, Long count, Date last, Rectangle area) {
        this.name = name;
        this.min = min;
        this.max = max;
        this.sum = sum;
        this.count = count;
        this.last = last;
        this.area = area;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getMin() {
        return min;
    }

    public Double getMax() {
        return max;
    }

    public Double getSum() {
        return sum;
    }

    public Long getCount() {
        return count;
    }

    public Date getLast() {
        return last;
    }

    public Rectangle getArea() {
        return area;
    }

    public Map<String, Double> getComputed() {
        return Collections.unmodifiableMap(computed);
    }

    public void addComputedValue(String name, Double value) {
        this.computed.put(name, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnalysedValue that = (AnalysedValue) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getMin() != null ? !getMin().equals(that.getMin()) : that.getMin() != null) return false;
        if (getMax() != null ? !getMax().equals(that.getMax()) : that.getMax() != null) return false;
        if (getSum() != null ? !getSum().equals(that.getSum()) : that.getSum() != null) return false;
        if (getCount() != null ? !getCount().equals(that.getCount()) : that.getCount() != null) return false;
        if (getComputed() != null ? !getComputed().equals(that.getComputed()) : that.getComputed() != null)
            return false;
        if (getLast() != null ? !getLast().equals(that.getLast()) : that.getLast() != null) return false;
        if (getArea() != null ? !getArea().equals(that.getArea()) : that.getArea() != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getMin() != null ? getMin().hashCode() : 0);
        result = 31 * result + (getMax() != null ? getMax().hashCode() : 0);
        result = 31 * result + (getSum() != null ? getSum().hashCode() : 0);
        result = 31 * result + (getCount() != null ? getCount().hashCode() : 0);
        result = 31 * result + (getComputed() != null ? getComputed().hashCode() : 0);
        result = 31 * result + (getLast() != null ? getLast().hashCode() : 0);
        result = 31 * result + (getArea() != null ? getArea().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AnalysedValue{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", min=" + min +
                ", max=" + max +
                ", sum=" + sum +
                ", count=" + count +
                ", computed=" + computed +
                ", last=" + last +
                ", area=" + area +
                '}';
    }
}
