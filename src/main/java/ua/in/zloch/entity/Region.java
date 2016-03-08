package ua.in.zloch.entity;

import org.json.JSONArray;
import org.json.JSONObject;
import ua.in.zloch.service.RegionService;

import javax.persistence.*;
import java.awt.geom.Path2D;
import java.io.Serializable;

@Entity
public class Region implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(optional = true)
    private Region parent;
    private String name;
    private Long koatuu;
    @Column(name = "boundariesLong2", length = 30000)
    private String boundaries;

    public Region() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Region getParent() {
        return parent;
    }

    public void setParent(Region parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getKoatuu() {
        return koatuu;
    }

    public void setKoatuu(Long koatuu) {
        this.koatuu = koatuu;
    }

    public String getBoundaries() {
        return boundaries;
    }

    public void setBoundaries(String boundaries) {
        this.boundaries = boundaries;
    }

    public Path2D getPolygon() {
        Path2D polygon = new Path2D.Double();
        JSONObject pointsData = new JSONObject("{points:" + getBoundaries() + "}");
        JSONArray pointsArray = pointsData.getJSONArray("points");
        polygon.moveTo(
                pointsArray.getJSONObject(0).getDouble("lat"),
                pointsArray.getJSONObject(0).getDouble("lng")
        );
        for (int i = 1; i < pointsArray.length(); i++) {
            polygon.lineTo(
                    pointsArray.getJSONObject(i).getDouble("lat"),
                    pointsArray.getJSONObject(i).getDouble("lng")
            );
        }

        return polygon;
    }
}
