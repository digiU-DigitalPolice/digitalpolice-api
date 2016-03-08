package ua.in.zloch.converters;

import org.junit.Test;
import ua.in.zloch.dto.CategoryDTO;
import ua.in.zloch.entity.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CategoryToCategoryDtoConverterTest {

    private CategoryToCategoryDtoConverter categoryToCategoryDtoConverter = new CategoryToCategoryDtoConverter();

    @Test
    public void testConvert() throws Exception {
        CategoryDTO categoryDTO = categoryToCategoryDtoConverter.convert(createTestCategory());

        assertNotNull(categoryDTO);

        assertEquals("DTP", categoryDTO.getTitle());
        assertEquals(new Long(1), categoryDTO.getId());
    }

    private Category createTestCategory() {
        Category category = new Category();
        category.setId(1l);
        category.setTitle("DTP");

        return category;
    }

}