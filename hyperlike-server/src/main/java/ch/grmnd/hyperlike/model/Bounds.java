package ch.grmnd.hyperlike.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Value type representing geographic bounds defined by south-west and south-east.
 */
public class Bounds {

    private final LatLon southWest;
    private final LatLon northEast;

    @JsonCreator
    public Bounds(List<List<Double>> bounds) {
        this.southWest = new LatLon(bounds.get(0).get(0), bounds.get(0).get(1));
        this.northEast = new LatLon(bounds.get(1).get(0), bounds.get(1).get(1));
    }

    public Bounds(LatLon southWest, LatLon northEast) {
        this.southWest = southWest;
        this.northEast = northEast;
    }

    public LatLon getSouthWest() {
        return southWest;
    }

    public LatLon getNorthEast() {
        return northEast;
    }

    @JsonValue
    public Double[][] getAsList() {
        return new Double[][]{{southWest.getLat(), southWest.getLon()},{northEast.getLat(), northEast.getLon()}};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bounds bounds = (Bounds) o;

        if (southWest != null ? !southWest.equals(bounds.southWest) : bounds.southWest != null) return false;
        return !(northEast != null ? !northEast.equals(bounds.northEast) : bounds.northEast != null);

    }

    @Override
    public int hashCode() {
        int result = southWest != null ? southWest.hashCode() : 0;
        result = 31 * result + (northEast != null ? northEast.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Bounds{" +
                "southWest=" + southWest +
                ", northEast=" + northEast +
                '}';
    }

}
