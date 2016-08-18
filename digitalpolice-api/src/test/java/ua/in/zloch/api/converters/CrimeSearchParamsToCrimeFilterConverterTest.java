package ua.in.zloch.api.converters;

import org.junit.Test;
import ua.in.zloch.api.dto.CrimeSearchParameters;
import ua.in.zloch.api.dto.PointParameter;
import ua.in.zloch.core.dto.CrimeFilter;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CrimeSearchParamsToCrimeFilterConverterTest {

    private CrimeSearchParamsToCrimeFilterConverter converter = new CrimeSearchParamsToCrimeFilterConverter();

    @Test
    public void testConvert() throws Exception {
        CrimeFilter crimeFilter = converter.convert(createCrimeSearchParameters());
        assertNotNull(crimeFilter);
        assertEquals(3, crimeFilter.getPrecision());
        assertEquals(1456175351l, crimeFilter.getDateFrom().getTime());
        assertEquals(1456175782l, crimeFilter.getDateTo().getTime());
        assertEquals(24.11, crimeFilter.getSouthWest().getLatitude(), 0.0);
        assertEquals(52.78, crimeFilter.getSouthWest().getLongitude(), 0.0);
        assertEquals(21.45, crimeFilter.getNorthEast().getLatitude(), 0.0);
        assertEquals(50.73, crimeFilter.getNorthEast().getLongitude(), 0.0);
        assertEquals(Arrays.asList(35l, 17l, 21l), crimeFilter.getCategories());
    }

    private CrimeSearchParameters createCrimeSearchParameters() {
        CrimeSearchParameters params = new CrimeSearchParameters();

        PointParameter southWest = new PointParameter();
        southWest.setLatitude(24.11);
        southWest.setLongitude(52.78);
        params.setSouthWest(southWest);

        PointParameter northEast = new PointParameter();
        northEast.setLatitude(21.45);
        northEast.setLongitude(50.73);
        params.setNorthEast(northEast);

        params.setPrecision(3);
        params.setDateFrom(1456175351l);
        params.setDateTo(1456175782l);
        params.setCategories(Arrays.asList(35l, 17l, 21l));
        return params;
    }
}