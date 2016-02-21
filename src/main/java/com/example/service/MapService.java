package com.example.service;

import com.example.entity.Category;
import com.example.entity.Crime;
import com.example.entity.Filter;
import com.example.repository.definition.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MapService {
    private UserDAO userDAO;
    private FilterDAO filterDAO;
    private CrimeDAO crimeDAO;
    private CategoryDAO categoryDAO;
    private RegionDAO regionDAO;

    @Autowired
    public MapService(UserDAO userDAO, FilterDAO filterDAO, CrimeDAO crimeDAO, CategoryDAO categoryDAO, RegionDAO regionDAO) {
        this.userDAO = userDAO;
        this.filterDAO = filterDAO;
        this.crimeDAO = crimeDAO;
        this.categoryDAO = categoryDAO;
        this.regionDAO = regionDAO;
    }

    public List<Crime> filterCrimes(Filter filter){
        return crimeDAO.search(filter);
    }
}
