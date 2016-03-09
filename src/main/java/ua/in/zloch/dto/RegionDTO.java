package ua.in.zloch.dto;

import java.util.*;

public class RegionDTO {
    private long id;
    private String name;
    private long koatuu;
    private String boundaries;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getKoatuu() {
        return koatuu;
    }

    public void setKoatuu(long koatuu) {
        this.koatuu = koatuu;
    }

    public String getBoundaries() {
        return boundaries;
    }

    public void setBoundaries(String boundaries) {
        this.boundaries = boundaries;
    }
}