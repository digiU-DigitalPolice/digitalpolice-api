package ua.in.zloch.core.repository.custom;

import ua.in.zloch.core.entity.Region;

import java.util.List;

public interface RegionRepositoryCustom {
    List<Region> search(List<Long> regionIds);
}
