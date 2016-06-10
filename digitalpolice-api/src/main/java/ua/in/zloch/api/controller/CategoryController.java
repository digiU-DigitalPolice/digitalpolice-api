package ua.in.zloch.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.in.zloch.api.dto.CategoryDTO;
import ua.in.zloch.api.service.CategoryService;

import java.util.List;

@RestController
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping("/categories")
    public List<CategoryDTO> getCategories() {
        return categoryService.getAllCategories();
    }

}
