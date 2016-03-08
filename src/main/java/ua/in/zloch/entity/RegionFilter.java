package ua.in.zloch.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class RegionFilter implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ElementCollection(targetClass=Long.class)
    private List<Long> koatuuIds;
    public RegionFilter() {
    }

    public List<Long> getKoatuuIds() {
        return koatuuIds;
    }

    public void setKoatuuIds(List<Long> koatuuIds) {
        this.koatuuIds = koatuuIds;
    }
}
