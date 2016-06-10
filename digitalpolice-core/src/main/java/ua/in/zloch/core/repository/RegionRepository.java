package ua.in.zloch.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.in.zloch.core.entity.Region;
import ua.in.zloch.core.repository.custom.RegionRepositoryCustom;

public interface RegionRepository extends JpaRepository<Region, Long>, RegionRepositoryCustom {
}
