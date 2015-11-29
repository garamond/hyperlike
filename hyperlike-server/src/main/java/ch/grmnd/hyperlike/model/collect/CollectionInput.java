package ch.grmnd.hyperlike.model.collect;

import ch.grmnd.hyperlike.model.LatLon;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * Representation of a user's input to the survey; to be analyzed and reported.
 */
@Document
public final class CollectionInput implements Serializable {

    @Id
    @JsonIgnore
    private String id;

    private final LatLon pos;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private final Date tstamp;
    private final Map<String, Double> values;

    private CollectionInput() {
        pos = null;
        tstamp = null;
        values = null;
    }

    public CollectionInput(LatLon pos, Date tstamp, Map<String, Double> values) {
        this.pos = pos;
        this.tstamp = tstamp;
        this.values = values;
    }

    public LatLon getPos() {
        return pos;
    }

    public Date getTstamp() {
        return tstamp;
    }

    public Map<String, Double> getValues() {
        return Collections.unmodifiableMap(values);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CollectionInput that = (CollectionInput) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (pos != null ? !pos.equals(that.pos) : that.pos != null) return false;
        if (tstamp != null ? !tstamp.equals(that.tstamp) : that.tstamp != null) return false;
        return !(values != null ? !values.equals(that.values) : that.values != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (pos != null ? pos.hashCode() : 0);
        result = 31 * result + (tstamp != null ? tstamp.hashCode() : 0);
        result = 31 * result + (values != null ? values.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CollectionInput{" +
                "pos=" + pos +
                ", tstamp=" + tstamp +
                ", values=" + values +
                '}';
    }

}
