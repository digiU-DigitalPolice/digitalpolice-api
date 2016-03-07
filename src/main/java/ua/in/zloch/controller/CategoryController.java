package ua.in.zloch.controller;


import ua.in.zloch.dto.CategoryListDTO;
import ua.in.zloch.dto.CrimeListDTO;
import ua.in.zloch.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    private MapService mapService;
    private ConversionService conversionService;

    @Autowired
    public CategoryController(MapService mapService, ConversionService conversionService) {
        this.mapService = mapService;
        this.conversionService = conversionService;
    }

    @RequestMapping("/categories")
    public CategoryListDTO getCategories(){
        return conversionService.convert(mapService.getAllCategories(), CategoryListDTO.class);
    }

}
