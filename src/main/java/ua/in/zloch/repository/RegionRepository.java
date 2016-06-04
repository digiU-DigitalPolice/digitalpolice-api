package ua.in.zloch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.in.zloch.entity.Region;
import ua.in.zloch.repository.custom.RegionRepositoryCustom;

public interface RegionRepository extends JpaRepository<Region, Long>, RegionRepositoryCustom {}
