package ua.in.zloch.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import ua.in.zloch.dto.CategoryDTO;
import ua.in.zloch.repository.definition.CategoryDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {

    @Mock
    private CategoryDAO categoryDAO;

    @Mock
    private ConversionService conversionService;

    @InjectMocks
    private CategoryService categoryService;

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllCategories() throws Exception {
        when(conversionService.convert(any(ArrayList.class), any(TypeDescriptor.class), any(TypeDescriptor.class)))
                .thenReturn(Arrays.asList(new CategoryDTO(), new CategoryDTO()));

        List<CategoryDTO> categories = categoryService.getAllCategories();

        assertNotNull(categories);
        assertEquals(2, categories.size());
    }
}