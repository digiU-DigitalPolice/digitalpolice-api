package ua.in.zloch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;

import ua.in.zloch.dto.RegionDTO;
import ua.in.zloch.entity.Region;
import ua.in.zloch.repository.definition.RegionDAO;

@Service
public class RegionService {
    private RegionDAO regionDAO;
    private ConversionService conversionService;

    @Autowired
    public RegionService(RegionDAO regionDAO, ConversionService conversionService) {
        this.regionDAO = regionDAO;
        this.conversionService = conversionService;
    }

    public List<RegionDTO> getRegions(List<Long> koatuuIds){
        List regions = this.regionDAO.search(koatuuIds);

        TypeDescriptor originType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Region.class));
        TypeDescriptor targetType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(RegionDTO.class));

        return (List<RegionDTO>)conversionService.convert(regions, originType, targetType);
    }
}
