package ua.in.zloch.dto;

import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryListDtoTest {

    @Test
    public void testCategoryListDTOFields(){
        CategoryListDTO categoryListDTO = new CategoryListDTO();
        categoryListDTO.getCategories();

        assertEquals(0, categoryListDTO.getCategories().size());

        categoryListDTO.addCategory(new CategoryListDTO().new CategoryDTO());
        assertNotNull(categoryListDTO.getCategories());
        assertEquals(1, categoryListDTO.getCategories().size());
    }

    @Test
    public void testCategoryDTOValues(){
        CategoryListDTO.CategoryDTO categoryDTO = new CategoryListDTO().new CategoryDTO();

        categoryDTO.setId(1);
        categoryDTO.setTitle("DTP");

        assertNotNull(categoryDTO.getCategory());
        assertEquals((long) 1, categoryDTO.getCategory().get(CategoryListDTO.CategoryDTO.ID));
        assertEquals("DTP", categoryDTO.getCategory().get(CategoryListDTO.CategoryDTO.TITLE));
    }

}