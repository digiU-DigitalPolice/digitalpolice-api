package ua.in.zloch.service;

import ua.in.zloch.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public List<Region> getRegions(RegionFilter filter){
        return regionDAO.search(filter);
    }
}
