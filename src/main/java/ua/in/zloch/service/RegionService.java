package ua.in.zloch.service;

import ua.in.zloch.entity.Crime;
import ua.in.zloch.entity.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.in.zloch.entity.Region;
import ua.in.zloch.entity.User;
import ua.in.zloch.repository.definition.*;

import java.awt.geom.Path2D;
import java.io.*;
import java.util.List;

@Service
public class RegionService {
    private UserDAO userDAO;
    private FilterDAO filterDAO;
    private CrimeDAO crimeDAO;
    private CategoryDAO categoryDAO;
    private RegionDAO regionDAO;
    private MapService mapService;

    @Autowired
    public RegionService(UserDAO userDAO, FilterDAO filterDAO, CrimeDAO crimeDAO, CategoryDAO categoryDAO, RegionDAO regionDAO, MapService mapService) {
        this.userDAO = userDAO;
        this.filterDAO = filterDAO;
        this.crimeDAO = crimeDAO;
        this.categoryDAO = categoryDAO;
        this.regionDAO = regionDAO;
        this.mapService= mapService;
    }

    public void importRegions() {
        BufferedReader regionsFileBuffer = null;
        String line;
        String[] parts;
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            regionsFileBuffer = new BufferedReader(
                    new FileReader(
                            new File(classLoader.getResource("data/regions/list.txt").getFile())
                    )
            );
            do {
                line = regionsFileBuffer.readLine();
                if (line == null) break;
                parts = line.split(":");
                System.out.println("Importing the region " + parts[0] + ", number = " + parts[1]);
                this.importRegion(parts[0], Integer.parseInt(parts[1]));
                System.out.println("Finished importing the region " + parts[0] + ", number = " + parts[1]);
            } while (line != null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importRegion(String regionName, int regionNumber) {
        Region r1;
        String boundaries;
        try {
            r1 = this.findRegionByKoatuu(Integer.toString(regionNumber));
        } catch (Exception e) {
            r1 = new Region();
        }
        r1.setName(regionName);
        r1.setKoatuu(Integer.toString(regionNumber));
        r1.setParent(null);
        boundaries = this.loadBoundariesFromFileByRegionNumber(regionNumber);
        System.out.println(boundaries);
        r1.setBoundaries(boundaries);
        this.saveRegion(r1);

        this.updateAllCrimesInRegion(r1);
    }
    public static String trimNotAlphanums(String s) {
        return s.replaceAll("[^0-9. ]", "");
    }


    public String loadBoundariesFromFileByRegionNumber(int regionNumber) {
        BufferedReader boundariesFileBuffer = null;
        String line;
        String[] coordinates;
        String[] latitudeLongitudeParts;
        StringBuffer coordinatesAsJson = new StringBuffer();
        coordinatesAsJson.append("[");
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            boundariesFileBuffer = new BufferedReader(
                    new FileReader(
                            new File(classLoader.getResource("data/regions/" + regionNumber + ".txt").getFile())
                    )
            );

            do {
                line = boundariesFileBuffer.readLine();
                if (line == null) break;
                coordinates = line.split(" ");
                for (String coordinate: coordinates) {
                    latitudeLongitudeParts = coordinate.split(",");
                    if (coordinatesAsJson.length() > 1) {
                        coordinatesAsJson.append(",");
                    }
                    coordinatesAsJson.append(
                            "{lat:" + this.trimNotAlphanums(latitudeLongitudeParts[1]) +
                                    ",lng:" + this.trimNotAlphanums(latitudeLongitudeParts[0]) + "}"
                    );
                }
            } while (line != null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        coordinatesAsJson.append("]");
        return coordinatesAsJson.toString();
    }

    public List<Region> getAllRegions(){
        return regionDAO.getAll();
    }

    public void saveRegion(Region region) {
        try {
            regionDAO.create(region);
        } catch (Exception e) {
            throw e;
        }

        return;
    }

    public Region findRegionByKoatuu(String koatuu) {
        try {
            return this.regionDAO.searchByKoatuu(koatuu).get(0);
        } catch (Exception e) {
            throw e;
        }
    }

    public void updateAllCrimesInRegion(Region region) {
        List<Crime> allCrimes = crimeDAO.search(new Filter());
        Path2D polygon = region.getPolygon();
        System.out.println("Begin searching the region " + region.getName() + " among all the crimes and updating regions data");
        long i = 0;
        long n = allCrimes.size();
        for (Crime crime: allCrimes) {
            if (polygon.contains(crime.getLatitude(), crime.getLongitude())) {
                crime.setRegion(region);
                crimeDAO.update(crime);
            }
            i++;
            if (i % 100 == 0) {
                System.out.println("    Progress: Region: " + region.getName() + ", Crime # " + i + ", total = " + n);
            }
        }
        System.out.println("End searching the region " + region.getName() + " among all the crimes and updating regions data");
    }
}
