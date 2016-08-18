package ua.in.zloch.api.dto;

import java.util.Date;
import java.util.List;

public class CrimeSearchParameters {
    private List<Long> categories;
    private Date dateFrom;
    private Date dateTo;
    private PointParameter southWest;
    private PointParameter northEast;

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

}



