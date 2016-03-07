package ua.in.zloch.converters;

import ua.in.zloch.dto.CrimeListDTO;
import ua.in.zloch.entity.Category;
import ua.in.zloch.entity.Crime;
import ua.in.zloch.entity.Region;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class CrimeToCrimeDtoConverterTest {

    private CrimeToCrimeDtoConverter crimeToCrimeDtoConverter = new CrimeToCrimeDtoConverter();

    @Test
    public void testConvert() throws Exception {
        CrimeListDTO crimeListDTO = crimeToCrimeDtoConverter.convert(createTestCrimeList());

        assertNotNull(crimeListDTO);
        assertNotNull(crimeListDTO.getFeatures());
        assertEquals(1, crimeListDTO.getFeatures().size());

        CrimeListDTO.CrimeDTO crimeDTO = crimeListDTO.getFeatures().get(0);
        assertNotNull(crimeDTO.getGeometry());
        assertArrayEquals(new double[]{4.56, -1.23}, crimeDTO.getGeometry().getCoordinates(), 0.0);

        assertNotNull(crimeDTO.getProperties());
        Map<String, Object> expectedProperties = new HashMap<String, Object>();
        expectedProperties.put("id", 123l);
        expectedProperties.put("date", 1456175351l);
        expectedProperties.put("category.id", 394l);
        expectedProperties.put("region.name", "ЗАЛІЗНИЧНИЙ");
        expectedProperties.put("region.koatuu", 4610136300l);

        assertEqualsMapEpsilon(expectedProperties, crimeDTO.getProperties());
    }

    @Test
    public void testConvertEmptyList(){
        CrimeListDTO crimeListDTO = crimeToCrimeDtoConverter.convert(new ArrayList<Crime>());
        assertNotNull(crimeListDTO);
        assertNotNull(crimeListDTO.getFeatures());
        assertEquals(0, crimeListDTO.getFeatures().size());
    }

    @Test
    public void testNullRegion(){
        Crime crime = createTestCrime();
        crime.setRegion(null);
        List<Crime> testCrimeList = createTestCrimeList(crime);

        CrimeListDTO crimeListDTO = crimeToCrimeDtoConverter.convert(testCrimeList);
        assertNotNull(crimeListDTO);
    }

    private List<Crime> createTestCrimeList(Crime crime){
        List<Crime> list = new ArrayList<Crime>();
        list.add(crime);
        return list;
    }

    private List<Crime> createTestCrimeList(){
        return createTestCrimeList(createTestCrime());
    }

    private Crime createTestCrime() {
        Crime crime = new Crime();
        crime.setId(123l);
        crime.setLatitude(-1.23);
        crime.setLongitude(4.56);
        crime.setDate(new Date(1456175351l));
        crime.setCategory(createTestCategory());
        crime.setRegion(createTestRegion());
        return crime;
    }

    private Region createTestRegion() {
        Region region = new Region();
        region.setId(102l);
        region.setName("ЗАЛІЗНИЧНИЙ");
        region.setKoatuu(4610136300l);
        return region;
    }

    private Category createTestCategory() {
        Category category = new Category();
        category.setId(394l);
        return category;
    }

    public static void assertEqualsMapEpsilon(Map<String,Object> expected, Map<String,Object> actual) {
        assertEquals(expected.size(), actual.size());
        for(Map.Entry<String,Object> value:expected.entrySet()){
            Object actualValue = actual.get(value.getKey());
            assertNotNull(actualValue);
            assertEquals(value.getValue(), actualValue);
        }
    }
}