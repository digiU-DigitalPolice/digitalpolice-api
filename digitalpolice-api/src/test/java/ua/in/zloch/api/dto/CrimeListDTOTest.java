package ua.in.zloch.api.dto;

import org.junit.Test;
import ua.in.zloch.api.dto.geojson.Type;

import static org.junit.Assert.*;

public class CrimeListDTOTest {

    @Test
    public void testCrimeListDTODefaultValues() {
        CrimeListDTO crimeListDTO = new CrimeListDTO();

        assertEquals(Type.FeatureCollection, crimeListDTO.getType());
        assertNotNull(crimeListDTO.getFeatures());
        assertEquals(0, crimeListDTO.getFeatures().size());

    }

    @Test
    public void testCrimeDTODefaultValues() {
        CrimeListDTO.CrimeDTO crimeDTO = new CrimeListDTO().new CrimeDTO();

        assertEquals(Type.Feature, crimeDTO.getType());
        assertNotNull(crimeDTO.getGeometry());

        assertEquals(Type.Point, crimeDTO.getGeometry().getType());
        assertArrayEquals(new double[]{0.0, 0.0}, crimeDTO.getGeometry().getCoordinates(), 0.0);
    }

}