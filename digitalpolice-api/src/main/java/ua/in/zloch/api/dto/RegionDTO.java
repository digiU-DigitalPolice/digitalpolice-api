package ua.in.zloch.api.dto;

public class RegionDTO {
    private long id;
    private String name;
    private long koatuu;

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

}