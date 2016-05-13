package ua.in.zloch.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.in.zloch.dto.RegionDTO;
import ua.in.zloch.service.RegionService;

@RestController
public class RegionController {
    private RegionService regionService;

    @Autowired
    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @RequestMapping("/regions")
    public List<RegionDTO> get(ArrayList<Long> koatuuIds){
        return regionService.getRegions(koatuuIds);
    }
}
