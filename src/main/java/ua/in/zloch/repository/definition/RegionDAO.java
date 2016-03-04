package ua.in.zloch.repository.definition;

import ua.in.zloch.entity.Region;
import ua.in.zloch.entity.RegionFilter;
import ua.in.zloch.repository.GenericDAO;

import java.util.List;

public interface RegionDAO extends GenericDAO<Region,Long> {
    public List<Region> searchByKoatuu(String koatuu);
    public List<Region> search(RegionFilter filter);
}
