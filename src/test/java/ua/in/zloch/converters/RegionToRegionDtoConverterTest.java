package ua.in.zloch.converters;

import ua.in.zloch.dto.RegionDTO;
import ua.in.zloch.entity.Region;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class RegionToRegionDtoConverterTest {

    private RegionToRegionDtoConverter regionToRegionDtoConverter = new RegionToRegionDtoConverter();

    @Test
    public void testConvert() throws Exception {
        RegionDTO regionDTO = regionToRegionDtoConverter.convert(createTestRegion());

        assertNotNull(regionDTO);
        assertEquals(123l, regionDTO.getId());
        assertEquals("Frankivsky", regionDTO.getName());
        assertEquals(12312l, regionDTO.getKoatuu());
    }


    private Region createTestRegion() {
        Region region = new Region();
        region.setId(123l);
        region.setName("Frankivsky");
        region.setKoatuu(12312l);

        return region;
    }
}