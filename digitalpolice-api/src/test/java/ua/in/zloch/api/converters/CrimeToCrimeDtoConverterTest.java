package ua.in.zloch.api.converters;

import org.junit.Test;
import ua.in.zloch.api.dto.CrimeListDTO;
import ua.in.zloch.core.entity.Category;
import ua.in.zloch.core.entity.Crime;

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
        Map<String, Object> expectedProperties = new HashMap<>();
        expectedProperties.put("id", 123l);
        expectedProperties.put("date", 1456175351l);
        expectedProperties.put("category.id", 394l);

        assertEqualsMapEpsilon(expectedProperties, crimeDTO.getProperties());
    }

    @Test
    public void testConvertEmptyList() {
        CrimeListDTO crimeListDTO = crimeToCrimeDtoConverter.convert(new ArrayList<Crime>());
        assertNotNull(crimeListDTO);
        assertNotNull(crimeListDTO.getFeatures());
        assertEquals(0, crimeListDTO.getFeatures().size());
    }

    @Test
    public void testNullRegion() {
        Crime crime = createTestCrime();
        List<Crime> testCrimeList = createTestCrimeList(crime);

        CrimeListDTO crimeListDTO = crimeToCrimeDtoConverter.convert(testCrimeList);
        assertNotNull(crimeListDTO);
    }

    private List<Crime> createTestCrimeList(Crime crime) {
        List<Crime> list = new ArrayList<>();
        list.add(crime);
        return list;
    }

    private List<Crime> createTestCrimeList() {
        return createTestCrimeList(createTestCrime());
    }

    private Crime createTestCrime() {
        Crime crime = new Crime();
        crime.setId(123l);
        crime.setLatitude(-1.23);
        crime.setLongitude(4.56);
        crime.setDate(new Date(1456175351l));
        crime.setCategory(createTestCategory());
        return crime;
    }

    private Category createTestCategory() {
        Category category = new Category();
        category.setId(394l);
        return category;
    }

    public static void assertEqualsMapEpsilon(Map<String, Object> expected, Map<String, Object> actual) {
        assertEquals(expected.size(), actual.size());
        for (Map.Entry<String, Object> value : expected.entrySet()) {
            Object actualValue = actual.get(value.getKey());
            assertNotNull(actualValue);
            assertEquals(value.getValue(), actualValue);
        }
    }
}