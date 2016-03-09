package ua.in.zloch.service;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import ua.in.zloch.dto.RegionDTO;
import ua.in.zloch.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.in.zloch.repository.definition.*;

import java.awt.geom.Path2D;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

@Service
public class RegionService {
    private RegionDAO regionDAO;
    private ConversionService conversionService;

    @Autowired
    public RegionService(RegionDAO regionDAO, ConversionService conversionService) {
        this.regionDAO = regionDAO;
        this.conversionService = conversionService;
    }

    public List<RegionDTO> getRegions(RegionFilter filter){
        List regions = this.regionDAO.search(filter);

        TypeDescriptor originType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Region.class));
        TypeDescriptor targetType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(RegionDTO.class));

        return (List<RegionDTO>)conversionService.convert(regions, originType, targetType);
    }
}
