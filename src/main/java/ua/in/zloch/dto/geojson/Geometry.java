package ua.in.zloch.dto.geojson;

public class Geometry {
    private Type type;
    private double[] coordinates;

    public Type getType() {
        return type;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double latitude, double longitude) {
        this.coordinates = new double[]{latitude, longitude};
    }

    public void setType(Type type) {
        this.type = type;
    }
}