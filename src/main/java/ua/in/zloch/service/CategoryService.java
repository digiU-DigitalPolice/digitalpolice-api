package ua.in.zloch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;
import ua.in.zloch.dto.CategoryDTO;
import ua.in.zloch.entity.Category;
import ua.in.zloch.repository.definition.CategoryDAO;

import java.util.List;

@Service
public class CategoryService {

    private CategoryDAO categoryDAO;
    private ConversionService conversionService;

    @Autowired
    public CategoryService(CategoryDAO categoryDAO, ConversionService conversionService){
        this.categoryDAO = categoryDAO;
        this.conversionService = conversionService;
    }

    public List<CategoryDTO> getAllCategories() {
        List categories = this.categoryDAO.getAll();

        TypeDescriptor originType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Category.class));
        TypeDescriptor targetType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(CategoryDTO.class));
        return (List<CategoryDTO>)conversionService.convert(categories, originType, targetType);
    }

}
