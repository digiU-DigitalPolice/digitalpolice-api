package ua.in.zloch.api.converters;

import org.springframework.core.convert.converter.Converter;
import ua.in.zloch.api.dto.RegionDTO;
import ua.in.zloch.core.entity.Region;


public class RegionToRegionDtoConverter implements Converter<Region, RegionDTO> {
    public RegionDTO convert(Region region) {
        RegionDTO dto = new RegionDTO();
        dto.setId(region.getId());
        dto.setName(region.getName());
        dto.setKoatuu(region.getKoatuu());
        return dto;
    }
}
