package ch.grmnd.hyperlike.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Representation of a rectangular GeoJSON Polygon; used for querying MongoDB.
 */
public class Rectangle implements Serializable {

    private final String type = "Polygon";
    private final List<List<List<Double>>> coordinates = new ArrayList<>();

    private Rectangle() {}

    public Rectangle(LatLon southWest, LatLon northEast) {
        List<List<Double>> coords = new ArrayList<>();
        coords.add(Arrays.asList(southWest.getLon(), southWest.getLat()));
        coords.add(Arrays.asList(northEast.getLon(), southWest.getLat()));
        coords.add(Arrays.asList(northEast.getLon(), northEast.getLat()));
        coords.add(Arrays.asList(southWest.getLon(), northEast.getLat()));
        coords.add(Arrays.asList(southWest.getLon(), southWest.getLat()));
        this.coordinates.add(coords);
    }

    public List<List<List<Double>>> getCoordinates() {
        return coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rectangle rectangle = (Rectangle) o;

        if (type != null ? !type.equals(rectangle.type) : rectangle.type != null) return false;
        return !(coordinates != null ? !coordinates.equals(rectangle.coordinates) : rectangle.coordinates != null);

    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (coordinates != null ? coordinates.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Polygon{" +
                "type='" + type + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
}
