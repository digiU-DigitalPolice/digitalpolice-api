package ua.in.zloch.controller;


import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.convert.TypeDescriptor;
import ua.in.zloch.dto.CategoryDTO;
import ua.in.zloch.entity.Category;
import ua.in.zloch.service.CategoryService;
import ua.in.zloch.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping("/categories")
    public List<CategoryDTO> getCategories(){
        return categoryService.getAllCategories();
    }

}
