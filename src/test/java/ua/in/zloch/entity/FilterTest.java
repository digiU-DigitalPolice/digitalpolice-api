package ua.in.zloch.entity;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by dmitry on 08.03.16.
 */
public class FilterTest {
    @Test
    public void testGetId(){
        Filter filter = new Filter();
        filter.setId(1);

        assertEquals(1, filter.getId());
    }

    @Test
    public void testGetRegions(){
        Filter filter = new Filter();
        filter.setRegions(new ArrayList<Long>());

        assertNotNull(filter.getRegions());
        assertEquals(0, filter.getRegions().size());
    }

    @Test
    public void testGetCategories(){
        Filter filter = new Filter();
        filter.setCategories(new ArrayList<Long>());

        assertNotNull(filter.getCategories());
        assertEquals(0, filter.getCategories().size());
    }

    private static Date getDateFromString(String date) throws ParseException {
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.parse(date);
    }

    private static long timeStringToUnixtime(String date) {
        try {
            Date dateObject = getDateFromString(date);
            return dateObject.getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String timeStringToUnixtimeAsString(String date) {
        return Long.toString(FilterTest.timeStringToUnixtime(date));
    }

    @Test
    public void testGetDateFrom() throws ParseException {
        String testDateString = "2015/01/01";
        Date testDate = getDateFromString(testDateString);
        Filter filter = new Filter();
        filter.setDateFrom(FilterTest.timeStringToUnixtime(testDateString));

        assertEquals(testDate, filter.getDateFrom());
    }

    @Test
    public void testGetDateTo() throws ParseException {
        String testDateString = "2015/03/04";
        Date testDate = getDateFromString(testDateString);
        Filter filter = new Filter();
        filter.setDateTo(FilterTest.timeStringToUnixtime(testDateString));

        assertEquals(testDate, filter.getDateTo());
    }

    @Test
    public void testGetZoom() throws ParseException {
        Filter filter = new Filter();
        filter.setZoom(2);

        assertEquals(2, filter.getZoom());
    }

}
