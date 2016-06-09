package ua.in.zloch.core.repository.custom.impl;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.in.zloch.core.CoreContextConfig;
import ua.in.zloch.core.entity.Region;
import ua.in.zloch.core.repository.RegionRepository;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(CoreContextConfig.class)
public class RegionRepositoryImplTest {

    @Autowired
    private RegionRepository regionRepository;

    @After
    public void cleanUp() {
        Iterator<Region> regionIterator = regionRepository.findAll().iterator();
        while (regionIterator.hasNext()) {
            regionRepository.delete(regionIterator.next());
        }
    }

    @Test
    public void testSearchAllRegions() {
        createRegion(123l);
        createRegion(234l);

        List<Region> regionList = regionRepository.search(null);

        assertNotNull(regionList);
        assertEquals(2, regionList.size());
    }

    @Test
    public void testSearchRegionsByKoatuu() {
        createRegion(123l);
        createRegion(234l);
        createRegion(345l);

        List<Region> regionList = regionRepository.search(Arrays.asList(123l, 234l));

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
        regionRepository.save(region);
    }
}