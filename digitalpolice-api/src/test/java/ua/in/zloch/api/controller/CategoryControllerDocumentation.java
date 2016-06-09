package ua.in.zloch.api.controller;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentation;
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
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CityPoliceApplication.class)
@WebAppConfiguration
public class CategoryControllerDocumentation {

    @Rule
    public RestDocumentation restDocumentation = new RestDocumentation("target/generated-snippets");

    @Mock
    private CategoryService categoryService;

    @Mock
    private ConversionService conversionService;

    @InjectMocks
    private CategoryController categoryController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
                .apply(documentationConfiguration(this.restDocumentation).uris()
                        .withScheme("http")
                        .withHost("162.211.230.155")
                        .withPort(8080))
                .build();
    }

    @Test
    public void testGetCategories() throws Exception {
        when(categoryService.getAllCategories()).thenReturn(createTwoCategoriesDTO());

        mockMvc.perform(get("/categories")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedUnfilteredCategoriesListJSON(), true))
                .andDo(document("get-category",
                        preprocessResponse(prettyPrint()),
                        requestParameters()));
    }

    private String expectedUnfilteredCategoriesListJSON() {
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