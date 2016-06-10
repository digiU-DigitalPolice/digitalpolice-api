package ua.in.zloch.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.in.zloch.api.dto.RegionDTO;
import ua.in.zloch.api.service.RegionService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RegionController {
    private RegionService regionService;

    @Autowired
    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @RequestMapping("/regions")
    public List<RegionDTO> get(@RequestParam(value = "regionIds", required = false) ArrayList<Long> regionIds) {
        return regionService.getRegions(regionIds);
    }
}
