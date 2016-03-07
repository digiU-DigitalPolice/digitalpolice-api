package ua.in.zloch.converters;

import org.junit.Test;
import ua.in.zloch.dto.CategoryListDTO;
import ua.in.zloch.dto.RegionListDTO;
import ua.in.zloch.entity.Category;
import ua.in.zloch.entity.Region;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CategoryToCategoryDtoConverterTest {

    private CategoryToCategoryDtoConverter categoryToCategoryDtoConverter = new CategoryToCategoryDtoConverter();

    @Test
    public void testConvert() throws Exception {
        CategoryListDTO categoryListDTO = categoryToCategoryDtoConverter.convert(createTestCategoryList());

        assertNotNull(categoryListDTO);
        assertNotNull(categoryListDTO.getCategories());
        assertEquals(1, categoryListDTO.getCategories().size());

        CategoryListDTO.CategoryDTO categoryDTO = categoryListDTO.getCategories().get(0);
        assertNotNull(categoryDTO.getCategory());
        assertEquals("DTP", categoryDTO.getCategory().get(CategoryListDTO.CategoryDTO.TITLE));

        assertNotNull(categoryDTO.getCategory());
        Map<String, Object> expectedProperties = new HashMap<String, Object>();
        Category testCategory = createTestCategory();
        expectedProperties.put(CategoryListDTO.CategoryDTO.ID, testCategory.getId());
        expectedProperties.put(CategoryListDTO.CategoryDTO.TITLE, testCategory.getTitle());

        assertEqualsMapEpsilon(expectedProperties, categoryDTO.getCategory());
    }

    @Test
    public void testConvertEmptyList(){
        CategoryListDTO categoryListDTO = categoryToCategoryDtoConverter.convert(new ArrayList<Category>());
        assertNotNull(categoryListDTO);
        assertNotNull(categoryListDTO.getCategories());
        assertEquals(0, categoryListDTO.getCategories().size());
    }

    private List<Category> createTestCategoryList(Category category){
        List<Category> list = new ArrayList<Category>();
        list.add(category);
        return list;
    }

    private List<Category> createTestCategoryList(){
        return this.createTestCategoryList(createTestCategory());
    }

    private Category createTestCategory() {
        Category category = new Category();
        category.setId(1l);
        category.setTitle("DTP");

        return category;
    }


    public static void assertEqualsMapEpsilon(Map<String,Object> expected, Map<String,Object> actual) {
        assertEquals(expected.size(), actual.size());
        for(Map.Entry<String,Object> value:expected.entrySet()){
            Object actualValue = actual.get(value.getKey());
            assertNotNull(actualValue);
            assertEquals(value.getValue(), actualValue);
        }
    }
}