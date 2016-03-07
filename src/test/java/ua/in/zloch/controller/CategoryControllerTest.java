package ua.in.zloch.controller;

import ua.in.zloch.CityPoliceApplication;
import ua.in.zloch.dto.CategoryListDTO;
import ua.in.zloch.dto.RegionListDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.in.zloch.service.MapService;
import ua.in.zloch.service.RegionService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CityPoliceApplication.class)
@WebAppConfiguration
public class CategoryControllerTest {

    @Mock
    private MapService mapService;

    @Mock
    private ConversionService conversionService;

    @InjectMocks
    private CategoryController categoryController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    public void testGetCategoriesList() throws Exception {
        when(conversionService.convert(any(List.class), any(Class.class))).thenReturn(createTwoCategoriesDTO());

        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedUnfilteredCategoryListJSON(), true));
    }

    private String expectedUnfilteredCategoryListJSON() {
        return readFromResource("ua.in.zloch/categories-list.json");
    }

    private String readFromResource(String resourceName) {
        try {
            return new Scanner(new ClassPathResource(resourceName).getInputStream()).useDelimiter("\\A").next();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private CategoryListDTO createTwoCategoriesDTO() {
        CategoryListDTO dtoList = new CategoryListDTO();

        CategoryListDTO.CategoryDTO dto = new CategoryListDTO().new CategoryDTO();
        dto.setId(123l);
        dto.setTitle("DTP");
        dtoList.addCategory(dto);

        CategoryListDTO.CategoryDTO dtoTwo = new CategoryListDTO().new CategoryDTO();
        dtoTwo.setId(124l);
        dtoTwo.setTitle("Murder");
        dtoList.addCategory(dtoTwo);

        return dtoList;
    }
}