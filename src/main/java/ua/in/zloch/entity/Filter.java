package ua.in.zloch.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Filter implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ElementCollection(targetClass=Long.class)
    private List<Long> regions;
    @ElementCollection(targetClass=Long.class)
    private List<Long> categories;
    private Date dateFrom;
    private Date dateTo;
    private int zoom;

    public Filter() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Long> getRegions() {
        return regions;
    }

    public void setRegions(List<Long> regions) {
        this.regions = regions;
    }

    public List<Long> getCategories() {
        return categories;
    }

    public void setCategories(List<Long> categories) {
        this.categories = categories;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(long dateFromUnixtime) {
        this.dateFrom = new java.util.Date(dateFromUnixtime);
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(long dateToUnixtime) {
        this.dateTo = new java.util.Date(dateToUnixtime);
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }
}
