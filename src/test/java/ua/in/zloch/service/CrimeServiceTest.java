package ua.in.zloch.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.ConversionService;
import ua.in.zloch.dto.CrimeFilter;
import ua.in.zloch.dto.CrimeListDTO;
import ua.in.zloch.repository.CrimeRepository;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class CrimeServiceTest {

    @Mock
    private CrimeRepository crimeRepository;

    @Mock
    private ConversionService conversionService;

    @InjectMocks
    private CrimeService crimeService;

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFilterCrimes() throws Exception {
        when(conversionService.convert(any(ArrayList.class), eq(CrimeListDTO.class)))
                .thenReturn(new CrimeListDTO());

        CrimeListDTO crimesListDTO = crimeService.filterCrimes(new CrimeFilter());

        assertNotNull(crimesListDTO);
    }
}