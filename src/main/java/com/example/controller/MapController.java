package com.example.controller;

import com.example.dto.CrimeListDTO;
import com.example.entity.Filter;
import com.example.entity.User;
import com.example.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
