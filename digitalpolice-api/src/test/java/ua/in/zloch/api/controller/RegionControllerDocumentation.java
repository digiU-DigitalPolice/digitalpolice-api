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
import ua.in.zloch.api.dto.RegionDTO;
import ua.in.zloch.api.service.RegionService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CityPoliceApplication.class)
@WebAppConfiguration
public class RegionControllerDocumentation {

    @Rule
    public RestDocumentation restDocumentation = new RestDocumentation("target/generated-snippets");

    @Mock
    private RegionService regionService;

    @Mock
    private ConversionService conversionService;

    @InjectMocks
    private RegionController regionController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(regionController)
                .apply(documentationConfiguration(this.restDocumentation).uris()
                        .withScheme("http")
                        .withHost("162.211.230.155")
                        .withPort(8080))
                .build();
    }

    @Test
    public void testGetRegions() throws Exception {
        when(regionService.getRegions(any(List.class))).thenReturn(createTwoRegionDTOList());

        mockMvc.perform(get("/regions")
                .accept(MediaType.APPLICATION_JSON)
                .param("regionIds", "4610136300,4610136600"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedUnfilteredRegionsListJSON(), true))
                .andDo(document("get-region",
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                parameterWithName("regionIds").attributes().description("список koatuu id регіонів, записані через кому")
                        )));
    }

    private String expectedUnfilteredRegionsListJSON() {
        return readFromResource("ua/in/zloch/api/regions-list.json");
    }

    private String readFromResource(String resourceName) {
        try {
            return new Scanner(new ClassPathResource(resourceName).getInputStream()).useDelimiter("\\A").next();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<RegionDTO> createTwoRegionDTOList() {
        List<RegionDTO> dtoList = new ArrayList<RegionDTO>();
        RegionDTO dto = new RegionDTO();
        dto.setId(123l);
        dto.setName("Frankivsky");
        dto.setKoatuu(12312l);
        dtoList.add(dto);

        RegionDTO dtoTwo = new RegionDTO();
        dtoTwo.setId(1232);
        dtoTwo.setName("Galytsky");
        dtoTwo.setKoatuu(12312l);
        dtoList.add(dtoTwo);
        return dtoList;
    }
}