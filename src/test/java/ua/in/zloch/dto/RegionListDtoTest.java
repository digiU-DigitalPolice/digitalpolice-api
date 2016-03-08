package ua.in.zloch.dto;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegionListDtoTest {

    @Test
    public void testRegionListDTOFields(){
        RegionListDTO regionListDTO = new RegionListDTO();
        regionListDTO.getRegions();

        assertEquals(0, regionListDTO.getRegions().size());

        regionListDTO.addRegion(new RegionListDTO().new RegionDTO());
        assertNotNull(regionListDTO.getRegions());
        assertEquals(1, regionListDTO.getRegions().size());
    }

    @Test
    public void testCrimeDTODefaultValues(){
        RegionListDTO.RegionDTO regionDTO = new RegionListDTO().new RegionDTO();

        regionDTO.setId(1);
        regionDTO.setName("Frankivsky");
        regionDTO.setKoatu(12345l);
        regionDTO.setBoundaries("[]");

        assertNotNull(regionDTO.getRegion());
        assertEquals((long) 1, regionDTO.getRegion().get(RegionListDTO.RegionDTO.ID));
        assertEquals("Frankivsky", regionDTO.getRegion().get(RegionListDTO.RegionDTO.NAME));
        assertEquals(12345l, regionDTO.getRegion().get(RegionListDTO.RegionDTO.KOATU));
        assertEquals("[]", regionDTO.getRegion().get(RegionListDTO.RegionDTO.BOUNDARIES));
    }

}