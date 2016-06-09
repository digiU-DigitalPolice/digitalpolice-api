package ua.in.zloch.api.converters;

import org.junit.Test;
import ua.in.zloch.api.dto.RegionDTO;
import ua.in.zloch.core.entity.Region;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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