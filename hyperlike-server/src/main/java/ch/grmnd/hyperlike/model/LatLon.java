package ch.grmnd.hyperlike.model;

import java.io.Serializable;

/**
 * Value type representing a latitude/longitude combination.
 */
public final class LatLon implements Serializable {

    private final Double lat;
    private final Double lon;

    private LatLon() {
        lat = null;
        lon = null;
    }

    public LatLon(Double lat, Double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }

    @Override
    public String toString() {
        return "Position{" +
                "lat=" + lat +
                ", lon=" + lon +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LatLon latLon = (LatLon) o;

        if (lat != null ? !lat.equals(latLon.lat) : latLon.lat != null) return false;
        return !(lon != null ? !lon.equals(latLon.lon) : latLon.lon != null);

    }

    @Override
    public int hashCode() {
        int result = lat != null ? lat.hashCode() : 0;
        result = 31 * result + (lon != null ? lon.hashCode() : 0);
        return result;
    }
}
