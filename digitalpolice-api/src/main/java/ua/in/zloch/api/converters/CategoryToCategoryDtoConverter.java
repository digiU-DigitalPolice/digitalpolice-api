package ua.in.zloch.api.converters;

import org.springframework.core.convert.converter.Converter;
import ua.in.zloch.api.dto.CategoryDTO;
import ua.in.zloch.core.entity.Category;

public class CategoryToCategoryDtoConverter implements Converter<Category, CategoryDTO> {
    @Override
    public CategoryDTO convert(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setTitle(category.getTitle());
        if (category.getParent() != null) {
            dto.setParentId(category.getParent().getId());
        }
        return dto;
    }
}
