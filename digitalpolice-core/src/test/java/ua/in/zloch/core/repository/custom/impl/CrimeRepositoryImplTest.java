package ua.in.zloch.core.repository.custom.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.in.zloch.core.CoreContextConfig;
import ua.in.zloch.core.dto.CrimeFilter;
import ua.in.zloch.core.dto.Point;
import ua.in.zloch.core.entity.Category;
import ua.in.zloch.core.entity.Crime;
import ua.in.zloch.core.entity.Region;
import ua.in.zloch.core.repository.CategoryRepository;
import ua.in.zloch.core.repository.CrimeRepository;
import ua.in.zloch.core.repository.RegionRepository;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(CoreContextConfig.class)
public class CrimeRepositoryImplTest {

    @Autowired
    private CrimeRepository crimeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RegionRepository regionRepository;

    private CoordinatesLibrary coordinatesLibrary = new CoordinatesLibrary();

    @Before
    public void before() {
        coordinatesLibrary.init();
    }

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
        new CrimeBuilder().save();
        new CrimeBuilder().save();

        // When
        List<Crime> crimeList = crimeRepository.search(new FilterBuilder().build());

        // Then
        assertNotNull(crimeList);
        assertEquals(2, crimeList.size());
    }

    @Test
    public void testCrimeProperties() {
        // Given
        Category robbery = createCategory("robbery");
        Crime testCrime = new CrimeBuilder()
                .withCategory(robbery)
                .withLatitude(49.8484259922661d)
                .withLongitude(24.039148092269897d)
                .withTimeStamp(1300000001)
                .save();

        // When
        List<Crime> crimeList = crimeRepository.search(new FilterBuilder().build());

        // Then
        Crime foundCrime = crimeList.get(0);
        assertEquals("robbery", foundCrime.getCategory().getTitle());
        assertEquals(49.8484259922661d, foundCrime.getLatitude(), 0.0);
        assertEquals(24.039148092269897d, foundCrime.getLongitude(), 0.0);
        assertEquals(1300000001, foundCrime.getDate().getTime());
        assertEquals(testCrime.getId(), foundCrime.getId());
    }

    @Test
    public void testSearchFilteredByFromDate() {
        // Given
        new CrimeBuilder().withTimeStamp(1100000000).save();
        new CrimeBuilder().withTimeStamp(1300000000).save();

        // When
        List<Crime> crimeList = crimeRepository.search(new FilterBuilder().withDateFrom(1200000000).build());

        // Then
        assertNotNull(crimeList);
        assertEquals(1, crimeList.size());
        assertEquals(1300000000, crimeList.get(0).getDate().getTime());
    }

    @Test
    public void testSearchFilteredByUntilDate() {
        // Given
        new CrimeBuilder().withTimeStamp(1100000000).save();
        new CrimeBuilder().withTimeStamp(1300000000).save();

        // When
        List<Crime> crimeList = crimeRepository.search(new FilterBuilder().withDateTo(1200000000).build());

        // Then
        assertNotNull(crimeList);
        assertEquals(1, crimeList.size());
        assertEquals(1100000000l, crimeList.get(0).getDate().getTime());
    }

    @Test
    public void testSearchFilteredByCategories() {
        // Given
        Category robbery = createCategory("robbery");
        Category parking = createCategory("parking");
        Category fraud = createCategory("fraud");

        new CrimeBuilder().withCategory(robbery).save();
        new CrimeBuilder().withCategory(parking).save();
        new CrimeBuilder().withCategory(fraud).save();

        // When
        List<Crime> crimeList = crimeRepository.search(new FilterBuilder().withCategories(robbery.getId(),
                fraud.getId()).build());

        // Then
        assertNotNull(crimeList);
        assertEquals(2, crimeList.size());

        Set<String> crimeCategories = extractCategoryTitles(crimeList);
        assertTrue(crimeCategories.contains("robbery"));
        assertTrue(crimeCategories.contains("fraud"));
    }

    @Test
    public void testSearchCrimesWithDifferentDatesByMultipleParameters() {
        // Given
        Category robbery = createCategory("robbery");
        new CrimeBuilder().withTimeStamp(1100000000).withCategory(robbery).save();

        new CrimeBuilder().withTimeStamp(1300000000).withCategory(robbery).save();

        // When
        List<Crime> crimeList = crimeRepository.search(new FilterBuilder()
                .withCategories(robbery.getId())
                .withDateFrom(1200000000)
                .build());

        // Then
        assertNotNull(crimeList);
        assertEquals(1, crimeList.size());

        Crime crime = crimeList.get(0);
        assertEquals("robbery", crime.getCategory().getTitle());
        assertEquals(1300000000l, crime.getDate().getTime());
    }

    @Test
    public void testSearchCrimesWithDifferentCategoriesByMultipleParameters() {
        // Given
        Category robbery = createCategory("robbery");
        new CrimeBuilder().withTimeStamp(1100000000).withCategory(robbery).save();

        Category parking = createCategory("parking");
        new CrimeBuilder().withTimeStamp(1100000000).withCategory(parking).save();

        // When
        List<Crime> crimeList = crimeRepository.search(new FilterBuilder()
                .withCategories(parking.getId())
                .withDateFrom(1000000000)
                .build());

        // Then
        assertNotNull(crimeList);
        assertEquals(1, crimeList.size());

        Crime crime = crimeList.get(0);
        assertEquals("parking", crime.getCategory().getTitle());
        assertEquals(1100000000, crime.getDate().getTime());
    }

    @Test
    public void testFindClusteredCrimesInCityCenter() {
        // Given
        // Vysokyy zamok
        new CrimeBuilder().withLatitude(49.8484259922661d).withLongitude(24.039148092269897d).save();

        // Ratusha
        new CrimeBuilder().withLatitude(49.84162114809589d).withLongitude(24.031708985567093d).save();

        //Kyiv
        new CrimeBuilder().withLatitude(50.448074405401776d).withLongitude(30.525253862142563d).save();

        // When
        List<Crime> crimeList = crimeRepository.search(new FilterBuilder()
                .withSouthWest(new Point(49.83740120522326d, 24.003539085388184d))
                .withNorthEast(new Point(49.85323079319976d, 24.057998657226562d))
                .build());

        // Then
        assertEquals(2, crimeList.size());
        assertEquals(49.8484259922661d, crimeList.get(0).getLatitude(), 0.0);
        assertEquals(24.039148092269897d, crimeList.get(0).getLongitude(), 0.0);

        assertEquals(49.84162114809589d, crimeList.get(1).getLatitude(), 0.0);
        assertEquals(24.031708985567093d, crimeList.get(1).getLongitude(), 0.0);

    }

    private Region createRegion(Long koatuu) {
        Region region = new Region();
        region.setKoatuu(koatuu);
        regionRepository.save(region);
        return region;
    }

    private Category createCategory(String name) {
        Category category = new Category();
        category.setTitle(name);
        categoryRepository.save(category);
        return category;
    }

    private Set<String> extractCategoryTitles(List<Crime> crimeList) {
        Set<String> crimeCategories = new HashSet<>();
        for (Crime crime : crimeList) {
            crimeCategories.add(crime.getCategory().getTitle());
        }
        return crimeCategories;
    }

    class CrimeBuilder {
        private Crime crime;

        public CrimeBuilder() {
            this.crime = new Crime();

            Coordinate coordinate = coordinatesLibrary.nextCoordinate();
            this.crime.setLatitude(coordinate.latitude);
            this.crime.setLongitude(coordinate.longitude);
        }

        public CrimeBuilder withTimeStamp(long timestamp) {
            this.crime.setDate(new Date(timestamp));
            return this;
        }

        public CrimeBuilder withCategory(Category category) {
            this.crime.setCategory(category);
            return this;
        }

        public CrimeBuilder withLatitude(double latitude) {
            this.crime.setLatitude(latitude);
            return this;
        }

        public CrimeBuilder withLongitude(double longitude) {
            this.crime.setLongitude(longitude);
            return this;
        }

        public Crime save() {
            return crimeRepository.save(this.crime);
        }
    }

    class FilterBuilder {

        private CrimeFilter filter;

        /**
         * Default south-west and north-east values are bound to initial coordinates in
         */
        public FilterBuilder() {
            filter = new CrimeFilter();
            filter.setSouthWest(new Point(49.83740120522326d, 24.003539085388184d));
            filter.setNorthEast(new Point(49.85323079319976d, 24.057998657226562d));
        }

        public FilterBuilder withDateFrom(long timestamp) {
            this.filter.setDateFrom(new Date(timestamp));
            return this;
        }

        public FilterBuilder withDateTo(long timestamp) {
            this.filter.setDateTo(new Date(timestamp));
            return this;
        }

        public FilterBuilder withCategories(Long... categories) {
            this.filter.setCategories(Arrays.asList(categories));
            return this;
        }

        public FilterBuilder withSouthWest(Point southWest) {
            this.filter.setSouthWest(southWest);
            return this;
        }

        public FilterBuilder withNorthEast(Point northEast) {
            this.filter.setNorthEast(northEast);
            return this;
        }

        public CrimeFilter build() {
            return this.filter;
        }
    }

    class CoordinatesLibrary {
        Queue<Coordinate> q = new LinkedList<>();

        public void init() {
            q.clear();

            // Vysokyy zamok
            q.add(new Coordinate(49.8484259922661d, 24.039148092269897d));
            // Ratusha
            q.add(new Coordinate(49.84162114809589d, 24.031708985567093d));
            // Opernyy
            q.add(new Coordinate(49.84411151134406d, 24.026183634996414d));
        }

        public Coordinate nextCoordinate() {
            if (q.isEmpty()) {
                throw new RuntimeException("Coordinates queue is empty. Fill it with more coordinates.");
            }
            return q.poll();
        }
    }

    class Coordinate {

        public Coordinate(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public double latitude;
        public double longitude;
    }
}
