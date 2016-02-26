package ua.in.zloch.controller;

import ua.in.zloch.dto.CrimeListDTO;
import ua.in.zloch.entity.Filter;
import ua.in.zloch.entity.User;
import ua.in.zloch.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MapController {

    private MapService mapService;
    private ConversionService conversionService;

    @Autowired
    public MapController(MapService mapService, ConversionService conversionService) {
        this.mapService = mapService;
        this.conversionService = conversionService;
    }

    @RequestMapping("/")
    public User hello(){
        return new User("username");
    }

    @RequestMapping("/map")
    public CrimeListDTO filterCrimes(Filter filter){
        return conversionService.convert(mapService.filterCrimes(filter), CrimeListDTO.class);
    }

}
