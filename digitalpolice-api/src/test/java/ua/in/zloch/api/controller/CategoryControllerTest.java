package ua.in.zloch.api.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.in.zloch.api.CityPoliceApplication;
import ua.in.zloch.api.dto.CategoryDTO;
import ua.in.zloch.api.service.CategoryService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CityPoliceApplication.class)
@WebAppConfiguration
public class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

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
        when(categoryService.getAllCategories()).thenReturn(createTwoCategoriesDTO());

        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedUnfilteredCategoryListJSON(), true));
    }

    private String expectedUnfilteredCategoryListJSON() {
        return readFromResource("ua/in/zloch/api/categories-list.json");
    }

    private String readFromResource(String resourceName) {
        try {
            return new Scanner(new ClassPathResource(resourceName).getInputStream()).useDelimiter("\\A").next();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<CategoryDTO> createTwoCategoriesDTO() {
        CategoryDTO dtoOne = new CategoryDTO();
        dtoOne.setId(123l);
        dtoOne.setTitle("DTP");
        dtoOne.setParentId(567l);

        CategoryDTO dtoTwo = new CategoryDTO();
        dtoTwo.setId(124l);
        dtoTwo.setTitle("Murder");
        dtoTwo.setParentId(890l);

        List<CategoryDTO> dtoList = new ArrayList<>();
        dtoList.add(dtoOne);
        dtoList.add(dtoTwo);

        return dtoList;
    }
}