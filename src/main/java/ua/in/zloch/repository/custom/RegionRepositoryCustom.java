package ua.in.zloch.repository.custom;

import ua.in.zloch.entity.Region;

import java.util.List;

public interface RegionRepositoryCustom {
    List<Region> search(List<Long> regionIds);
}
