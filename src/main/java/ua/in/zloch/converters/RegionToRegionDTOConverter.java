package ua.in.zloch.converters;

import ua.in.zloch.dto.RegionListDTO;
import org.springframework.core.convert.converter.Converter;
import ua.in.zloch.entity.Region;

import java.util.List;



public class RegionToRegionDTOConverter implements Converter<List<Region>, RegionListDTO> {
    @Override
    public RegionListDTO convert(List<Region> regionList) {
        RegionListDTO dtoList = new RegionListDTO();
        for (Region region : regionList) {
            RegionListDTO.RegionDTO dto = new RegionListDTO().new RegionDTO();
            dto.setId(region.getId());
            dto.setName(region.getName());
            dto.setKoatu(region.getKoatuu());
            dto.setBoundaries(region.getBoundaries());
            dtoList.addRegion(dto);
        }
        return dtoList;
    }
}
