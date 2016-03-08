package ua.in.zloch.dto;

import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryDtoTest {

    @Test
    public void testCategoryDTOValues(){
        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId(1l);
        categoryDTO.setTitle("DTP");

        assertEquals(new Long(1), categoryDTO.getId());
        assertEquals("DTP", categoryDTO.getTitle());
    }

}