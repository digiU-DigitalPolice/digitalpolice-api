package ua.in.zloch.converters;

import ua.in.zloch.dto.CategoryListDTO;
import org.springframework.core.convert.converter.Converter;
import ua.in.zloch.entity.Category;

import java.util.List;

public class CategoryToCategoryDtoConverter implements Converter<List<Category>, CategoryListDTO> {
    @Override
    public CategoryListDTO convert(List<Category> categoriesList) {
        CategoryListDTO dtoList = new CategoryListDTO();
        for (Category category: categoriesList) {
            CategoryListDTO.CategoryDTO dto = new CategoryListDTO().new CategoryDTO();
            dto.setId(category.getId());
            dto.setTitle(category.getTitle());
            dtoList.addCategory(dto);
        }
        return dtoList;
    }
}
