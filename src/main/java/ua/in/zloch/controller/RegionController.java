package ua.in.zloch.controller;

import ua.in.zloch.dto.RegionDTO;
import ua.in.zloch.entity.RegionFilter;
import ua.in.zloch.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.in.zloch.service.RegionService;

import java.util.List;

@RestController
public class RegionController {
    private RegionService regionService;

    @Autowired
    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @RequestMapping("/regions")
    public List<RegionDTO> get(RegionFilter filter){
        return regionService.getRegions(filter);
    }
}
