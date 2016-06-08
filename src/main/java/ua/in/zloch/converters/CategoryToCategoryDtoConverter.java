package ua.in.zloch.converters;

import ua.in.zloch.dto.CategoryDTO;
import org.springframework.core.convert.converter.Converter;
import ua.in.zloch.entity.Category;

import java.util.List;

public class CategoryToCategoryDtoConverter implements Converter<Category, CategoryDTO> {
    @Override
    public CategoryDTO convert(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setTitle(category.getTitle());
        if(category.getParent() != null) {
            dto.setParentId(category.getParent().getId());
        }
        return dto;
    }
}
