package ua.in.zloch.api.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class CrimeSearchParameters {
    private List<Long> regions;
    private List<Long> categories;
    private Date dateFrom;
    private Date dateTo;
    private PointParameter southWest;
    private PointParameter northEast;
    private int precision;

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

    public void setDateFrom(long dateFrom) {
        this.dateFrom = new Date(dateFrom);
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(long dateTo) {
        this.dateTo = new Date(dateTo);
    }

    public PointParameter getSouthWest() {
        return southWest;
    }

    public void setSouthWest(PointParameter southWest) {
        this.southWest = southWest;
    }

    public PointParameter getNorthEast() {
        return northEast;
    }

    public void setNorthEast(PointParameter northEast) {
        this.northEast = northEast;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

}



