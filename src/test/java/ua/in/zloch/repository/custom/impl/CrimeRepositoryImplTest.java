package ua.in.zloch.repository.custom.impl;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.in.zloch.CityPoliceApplication;
import ua.in.zloch.dto.CrimeFilter;
import ua.in.zloch.entity.Category;
import ua.in.zloch.entity.Crime;
import ua.in.zloch.entity.Region;
import ua.in.zloch.repository.CategoryRepository;
import ua.in.zloch.repository.CrimeRepository;
import ua.in.zloch.repository.RegionRepository;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(CityPoliceApplication.class)
public class CrimeRepositoryImplTest {

    @Autowired
    private CrimeRepository crimeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RegionRepository regionRepository;

    @After
    public void cleanUp() {
        Iterator<Crime> crimeIterator = crimeRepository.findAll().iterator();
        while (crimeIterator.hasNext()) {
            crimeRepository.delete(crimeIterator.next());
        }

        Iterator<Category> categoryIterator = categoryRepository.findAll().iterator();
        while (categoryIterator.hasNext()) {
            categoryRepository.delete(categoryIterator.next());
        }

        Iterator<Region> regionIterator = regionRepository.findAll().iterator();
        while (regionIterator.hasNext()) {
            regionRepository.delete(regionIterator.next());
        }
    }

    @Test
    public void testSearchWithEmptyFilter() {
        // Given
        createCrime();
        createCrime();

        // When
        List<Crime> crimeList = crimeRepository.search(new FilterBuilder().build());

        // Then
        assertNotNull(crimeList);
        assertEquals(2, crimeList.size());
    }

    @Test
    public void testSearchFilteredByFromDate() {
        // Given
        createCrime(1100000000);
        createCrime(1300000000);

        // When
        List<Crime> crimeList = crimeRepository.search(new FilterBuilder().withDateFrom(1200000000).build());

        // Then
        assertNotNull(crimeList);
        assertEquals(1, crimeList.size());
        //assertEquals(1300000000, crimeList.get(0).getDate().getTime());
    }

    @Test
    public void testSearchFilteredByUntilDate() {
        // Given
        createCrime(1100000000);
        createCrime(1300000000);

        // When
        List<Crime> crimeList = crimeRepository.search(new FilterBuilder().withDateTo(1200000000).build());

        // Then
        assertNotNull(crimeList);
        assertEquals(1, crimeList.size());
        //assertEquals(1100000000l, crimeList.get(0).getDate().getTime());
    }

    @Test
    public void testSearchFilteredByCategories() {
        // Given
        Category robbery = createCategory("robbery");
        Category parking = createCategory("parking");
        Category fraud = createCategory("fraud");

        createCrime(robbery);
        createCrime(parking);
        createCrime(fraud);

        // When
        List<Crime> crimeList = crimeRepository.search(new FilterBuilder().withCategories(robbery.getId(), fraud.getId()).build());

        // Then
        assertNotNull(crimeList);
        assertEquals(2, crimeList.size());

        Set<String> crimeCategories = extractCategoryTitles(crimeList);
        assertTrue(crimeCategories.contains("robbery"));
        assertTrue(crimeCategories.contains("fraud"));
    }

    @Test
    public void testSearchFilteredByRegions() {
        // Given
        Region district1 = createRegion(123l);
        Region district2 = createRegion(456l);
        Region district3 = createRegion(789l);

        createCrime(district1);
        createCrime(district2);
        createCrime(district3);

        // When
        List<Crime> crimeList = crimeRepository.search(new FilterBuilder().withRegions(district1.getKoatuu(), district3.getKoatuu()).build());

        // Then
        assertNotNull(crimeList);
        assertEquals(2, crimeList.size());

        Set<Long> crimeCategories = extractRegionKoatuus(crimeList);
        assertTrue(crimeCategories.contains(123l));
        assertTrue(crimeCategories.contains(789l));
    }

    private Set<Long> extractRegionKoatuus(List<Crime> crimeList) {
        Set<Long> crimeRegions = new HashSet<>();
        for (Crime crime : crimeList) {
            crimeRegions.add(crime.getRegion().getKoatuu());
        }
        return crimeRegions;
    }

    private void createCrime(Region region) {
        Crime crime = newCrime();
        crime.setRegion(region);
        crimeRepository.save(crime);
    }

    private Region createRegion(Long koatuu) {
        Region region = new Region();
        region.setKoatuu(koatuu);
        regionRepository.save(region);
        return region;
    }

    private Set<String> extractCategoryTitles(List<Crime> crimeList) {
        Set<String> crimeCategories = new HashSet<>();
        for (Crime crime : crimeList) {
            crimeCategories.add(crime.getCategory().getTitle());
        }
        return crimeCategories;
    }

    private Category createCategory(String name) {
        Category category = new Category();
        category.setTitle(name);
        categoryRepository.save(category);
        return category;
    }

    private Crime newCrime() {
        return newCrime(1464556537);
    }

    private Crime newCrime(long timestamp) {
        Crime crime = new Crime();
        crime.setDate(new Date(timestamp));
        return crime;
    }

    private void createCrime(Category category) {
        Crime crime = newCrime();
        crime.setCategory(category);
        crimeRepository.save(crime);
    }

    private void createCrime(long timestamp) {
        crimeRepository.save(newCrime(timestamp));
    }

    private void createCrime() {
        createCrime(1464556537); // 29/05/2016
    }

    class FilterBuilder {

        private CrimeFilter filter;

        public FilterBuilder() {
            filter = new CrimeFilter();
        }

        public FilterBuilder withDateFrom(long timestamp) {
            this.filter.setDateFrom(timestamp);
            return this;
        }

        public FilterBuilder withDateTo(long timestamp) {
            this.filter.setDateTo(timestamp);
            return this;
        }

        public FilterBuilder withCategories(Long... categories) {
            this.filter.setCategories(Arrays.asList(categories));
            return this;
        }

        public FilterBuilder withRegions(Long... regions) {
            this.filter.setRegions(Arrays.asList(regions));
            return this;
        }

        public CrimeFilter build() {
            return this.filter;
        }
    }

}
