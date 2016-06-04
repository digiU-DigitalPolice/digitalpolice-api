package ua.in.zloch.dto;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegionListDtoTest {

    @Test
    public void testCrimeDTODefaultValues(){
        RegionDTO regionDTO = new RegionDTO();

        regionDTO.setId(1);
        regionDTO.setName("Frankivsky");
        regionDTO.setKoatuu(12345l);

        assertNotNull(regionDTO);
        assertEquals((long) 1, regionDTO.getId());
        assertEquals("Frankivsky", regionDTO.getName());
        assertEquals(12345l, regionDTO.getKoatuu());
    }

}