package ua.in.zloch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;
import ua.in.zloch.dto.RegionDTO;
import ua.in.zloch.entity.Region;
import ua.in.zloch.repository.RegionRepository;

import java.util.List;

@Service
public class RegionService {
    private RegionRepository regionRepository;
    private ConversionService conversionService;

    @Autowired
    public RegionService(RegionRepository regionRepository, ConversionService conversionService) {
        this.regionRepository = regionRepository;
        this.conversionService = conversionService;
    }

    public List<RegionDTO> getRegions(List<Long> regionIds){
        List regions = this.regionRepository.search(regionIds);

        TypeDescriptor originType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Region.class));
        TypeDescriptor targetType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(RegionDTO.class));

        return (List<RegionDTO>)conversionService.convert(regions, originType, targetType);
    }
}
