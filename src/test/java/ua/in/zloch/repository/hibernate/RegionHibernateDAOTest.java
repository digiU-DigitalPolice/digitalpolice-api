package ua.in.zloch.repository.hibernate;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.in.zloch.CityPoliceApplication;
import ua.in.zloch.entity.Region;
import ua.in.zloch.repository.definition.RegionDAO;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(CityPoliceApplication.class)
public class RegionHibernateDAOTest {

    @Autowired
    private RegionDAO regionDAO;

    @After
    public void cleanUp() {
        Iterator<Region> regionIterator = regionDAO.getAll().iterator();
        while (regionIterator.hasNext()) {
            regionDAO.delete(regionIterator.next());
        }
    }

    @Test
    public void testSearchAllRegions() {
        createRegion(123l);
        createRegion(234l);

        List<Region> regionList = regionDAO.search(null);

        assertNotNull(regionList);
        assertEquals(2, regionList.size());
    }

    @Test
    public void testSearchRegionsByKoatuu() {
        createRegion(123l);
        createRegion(234l);
        createRegion(345l);

        List<Region> regionList = regionDAO.search(Arrays.asList(new Long[]{123l, 234l}));

        assertNotNull(regionList);
        assertEquals(2, regionList.size());
        Set<Long> regionKoatuus = extractRegionKoatuus(regionList);
        assertTrue(regionKoatuus.contains(123l));
        assertTrue(regionKoatuus.contains(234l));
    }

    private Set<Long> extractRegionKoatuus(List<Region> regionList) {
        Set<Long> regionKoatuus = new HashSet<>();
        for (Region region : regionList) {
            regionKoatuus.add(region.getKoatuu());
        }
        return regionKoatuus;
    }

    private void createRegion(long koatuu) {
        Region region = new Region();
        region.setKoatuu(koatuu);
        regionDAO.create(region);
    }
}