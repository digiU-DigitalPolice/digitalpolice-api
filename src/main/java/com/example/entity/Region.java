package com.example.entity;

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
    private String koatuu;
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

    public String getKoatuu() {
        return koatuu;
    }

    public void setKoatuu(String koatuu) {
        this.koatuu = koatuu;
    }

    public String getBoundaries() {
        return boundaries;
    }

    public void setBoundaries(String boundaries) {
        this.boundaries = boundaries;
    }
}
