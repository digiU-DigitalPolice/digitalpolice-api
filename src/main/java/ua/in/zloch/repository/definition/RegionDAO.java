package ua.in.zloch.repository.definition;

import ua.in.zloch.entity.Region;
import ua.in.zloch.repository.GenericDAO;

import java.util.List;

public interface RegionDAO extends GenericDAO<Region,Long> {
    List<Region> search(List<Long> regionIds);
}
