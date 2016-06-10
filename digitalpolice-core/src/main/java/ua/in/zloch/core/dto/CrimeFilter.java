package ua.in.zloch.core.dto;

import java.util.Date;
import java.util.List;

public class CrimeFilter {
    private List<Long> regions;
    private List<Long> categories;
    private Date dateFrom;
    private Date dateTo;

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

}
