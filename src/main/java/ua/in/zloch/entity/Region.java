package ua.in.zloch.entity;

import javax.persistence.*;
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

}
