package ua.in.zloch.api.controller;

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
import ua.in.zloch.api.CityPoliceApplication;
import ua.in.zloch.api.dto.RegionDTO;
import ua.in.zloch.api.service.RegionService;

import java.io.IOException;
import java.util.ArrayList;
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
public class RegionControllerTest {

    @Mock
    private RegionService regionService;

    @Mock
    private ConversionService conversionService;

    @InjectMocks
    private RegionController regionController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(regionController).build();
    }

    @Test
    public void testGetUnfilteredCrimesList() throws Exception {
        when(regionService.getRegions(any(List.class))).thenReturn(createTwoRegionDTO());

        mockMvc.perform(get("/regions"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedUnfilteredCrimeListJSON(), true));
    }

    private String expectedUnfilteredCrimeListJSON() {
        return readFromResource("ua/in/zloch/api/regions-list.json");
    }

    private String readFromResource(String resourceName) {
        try {
            return new Scanner(new ClassPathResource(resourceName).getInputStream()).useDelimiter("\\A").next();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<RegionDTO> createTwoRegionDTO() {
        ArrayList<RegionDTO> regionDtoList = new ArrayList<RegionDTO>();
        RegionDTO dto = new RegionDTO();

        dto.setId(123l);
        dto.setName("Frankivsky");
        dto.setKoatuu(12312l);
        regionDtoList.add(dto);

        RegionDTO dtoTwo = new RegionDTO();
        dtoTwo.setId(1232);
        dtoTwo.setName("Galytsky");
        dtoTwo.setKoatuu(12312l);
        regionDtoList.add(dtoTwo);

        return regionDtoList;
    }
}