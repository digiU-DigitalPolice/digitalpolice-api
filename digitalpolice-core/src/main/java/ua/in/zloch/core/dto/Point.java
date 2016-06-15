package ua.in.zloch.core.dto;

/**
 * Could not find any appropriate class in java api.
 */
public class Point {

    private double latitude;
    private double longitude;

    public Point(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

}
