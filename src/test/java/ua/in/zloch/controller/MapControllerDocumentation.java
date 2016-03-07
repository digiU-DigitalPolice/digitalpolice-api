package ua.in.zloch.controller;

import ua.in.zloch.CityPoliceApplication;
import ua.in.zloch.dto.CrimeListDTO;
import ua.in.zloch.service.MapService;
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

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CityPoliceApplication.class)
@WebAppConfiguration
public class MapControllerDocumentation {

    @Rule
    public RestDocumentation restDocumentation = new RestDocumentation("target/generated-snippets");

    @Mock
    private MapService mapService;

    @Mock
    private ConversionService conversionService;

    @InjectMocks
    private CrimeController crimeController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(crimeController)
                .apply(documentationConfiguration(this.restDocumentation).uris()
                        .withScheme("http")
                        .withHost("162.211.230.155")
                        .withPort(8080))
                .build();
    }

    @Test
    public void testGetCrimesMap() throws Exception {
        when(conversionService.convert(any(List.class), any(Class.class))).thenReturn(createCrimeDTO());

        mockMvc.perform(get("/crimes")
                .accept(MediaType.APPLICATION_JSON)
                .param("dateFrom", "2015/01/01")
                .param("dateTo", "2015/12/31")
                .param("categories", "1,2,3"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedUnfilteredCrimeListJSON(), true))
                .andDo(document("get-map",
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                parameterWithName("dateFrom").attributes().description("Початкова дата, від якої шукати злочини. Формат дати yyyy/mm/dd"),
                                parameterWithName("dateTo").description("Кінцева дата, до якої шукати злочини. Формат дати yyyy/mm/dd"),
                                parameterWithName("categories").description("id категорій до яких відносяться злочини, перелічені через кому")
                        )));
    }

    private String expectedUnfilteredCrimeListJSON() {
        return readFromResource("ua.in.zloch/single-crime.json");
    }

    private String readFromResource(String resourceName) {
        try {
            return new Scanner(new ClassPathResource(resourceName).getInputStream()).useDelimiter("\\A").next();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private CrimeListDTO createCrimeDTO() {
        CrimeListDTO dtoList = new CrimeListDTO();

        CrimeListDTO.CrimeDTO dto = new CrimeListDTO().new CrimeDTO();
        dto.setCoordinates(24.01499, 49.79653);
        dto.setId(1l);
        dto.setDate(new Date(1431986400000l));
        dto.setCategoryId(1l);
        dto.setRegionName("ЗАЛІЗНИЧНИЙ");
        dto.setRegionKoatuu("4610136300");
        dtoList.addFeature(dto);

        return dtoList;
    }
}