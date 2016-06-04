package ua.in.zloch.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import ua.in.zloch.dto.RegionDTO;
import ua.in.zloch.repository.RegionRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class RegionServiceTest {

    @Mock
    private RegionRepository regionRepository;

    @Mock
    private ConversionService conversionService;

    @InjectMocks
    private RegionService regionService;

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetRegions() {
        when(conversionService.convert(any(ArrayList.class), any(TypeDescriptor.class), any(TypeDescriptor.class)))
                .thenReturn(Arrays.asList(new RegionDTO(), new RegionDTO()));

        List<RegionDTO> regionDTOs = regionService.getRegions(Arrays.asList(123l, 456l));

        assertNotNull(regionDTOs);
        assertEquals(2, regionDTOs.size());
    }

}