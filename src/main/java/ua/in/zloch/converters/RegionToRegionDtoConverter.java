package ua.in.zloch.converters;

import ua.in.zloch.dto.RegionDTO;
import org.springframework.core.convert.converter.Converter;
import ua.in.zloch.entity.Region;

import java.util.List;



public class RegionToRegionDtoConverter implements Converter<Region, RegionDTO> {
    public RegionDTO convert(Region region) {
        RegionDTO dto = new RegionDTO();
        dto.setId(region.getId());
        dto.setName(region.getName());
        dto.setKoatuu(region.getKoatuu());
        dto.setBoundaries(region.getBoundaries());
        return dto;
    }
}
