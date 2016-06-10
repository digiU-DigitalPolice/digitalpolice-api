package ua.in.zloch.batch.geohash;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import ua.in.zloch.core.entity.Crime;

import static org.junit.Assert.*;

public class GeohashProcessorTest {

    @InjectMocks
    private GeohashProcessor geohashProcessor;

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testProcess() throws Exception {
        Crime crime = new Crime();
        crime.setLatitude(49.876543701141976);
        crime.setLongitude(24.043986797332764);

        geohashProcessor.process(crime);

        assertEquals("u8c5epsrct8h", crime.getGeohash());
    }
}