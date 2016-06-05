package ua.in.zloch.controller;

import ua.in.zloch.CityPoliceApplication;
import ua.in.zloch.dto.CrimeListDTO;
import ua.in.zloch.dto.CrimeFilter;
import ua.in.zloch.service.CrimeService;
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

import java.io.IOException;
import java.sql.Date;
import java.util.Scanner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CityPoliceApplication.class)
@WebAppConfiguration
public class CrimeControllerTest {

    @Mock
    private CrimeService crimeService;

    @InjectMocks
    private CrimeController crimeController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(crimeController).build();
    }

    @Test
    public void testGetUnfilteredCrimesList() throws Exception {
        when(crimeService.filterCrimes(any(CrimeFilter.class))).thenReturn(createTwoCrimesDTO());

        mockMvc.perform(get("/crimes"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedUnfilteredCrimeListJSON(), true));
    }

    private String expectedUnfilteredCrimeListJSON() {
        return readFromResource("ua.in.zloch/crimes-list.json");
    }

    private String readFromResource(String resourceName) {
        try {
            return new Scanner(new ClassPathResource(resourceName).getInputStream(), "UTF-8").useDelimiter("\\A").next();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private CrimeListDTO createTwoCrimesDTO() {
        CrimeListDTO dtoList = new CrimeListDTO();

        CrimeListDTO.CrimeDTO dto = new CrimeListDTO().new CrimeDTO();
        dto.setCoordinates(-111.538593, 37.000674);
        dto.setId(123l);
        dto.setDate(new Date(1456209116l));
        dto.setCategoryId(8l);
        dto.setRegionName("ЗАЛІЗНИЧНИЙ");
        dto.setRegionKoatuu(4610136300l);
        dtoList.addFeature(dto);

        CrimeListDTO.CrimeDTO dtoTwo = new CrimeListDTO().new CrimeDTO();
        dtoTwo.setCoordinates(-123.538593, 40.000674);
        dtoTwo.setId(456l);
        dtoTwo.setDate(new Date(1456209117l));
        dtoTwo.setCategoryId(3l);
        dtoTwo.setRegionName("undefined");
        dtoTwo.setRegionKoatuu(0l);
        dtoList.addFeature(dtoTwo);

        return dtoList;
    }
}