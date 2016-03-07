package ua.in.zloch.controller;

import ua.in.zloch.dto.RegionListDTO;
import ua.in.zloch.entity.Region;
import ua.in.zloch.entity.RegionFilter;
import ua.in.zloch.entity.User;
import ua.in.zloch.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.in.zloch.service.RegionService;

@RestController
public class RegionController {

    private MapService mapService;
    private RegionService regionService;
    private ConversionService conversionService;

    @Autowired
    public RegionController(MapService mapService, ConversionService conversionService, RegionService regionService) {
        this.mapService = mapService;
        this.conversionService = conversionService;
        this.regionService = regionService;
    }

    @RequestMapping("/regions")
    public RegionListDTO get(RegionFilter filter){
        return conversionService.convert(regionService.getRegions(filter), RegionListDTO.class);
    }
}
