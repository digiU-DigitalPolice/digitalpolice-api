package com.example.service;

import com.example.entity.Category;
import com.example.entity.Filter;
import com.example.repository.definition.CategoryDAO;
import com.example.repository.definition.CrimeDAO;
import com.example.repository.definition.FilterDAO;
import com.example.repository.definition.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MapService {
    private UserDAO userDAO;
    private FilterDAO filterDAO;
    private CrimeDAO crimeDAO;
    private CategoryDAO categoryDAO;

    @Autowired
    public MapService(UserDAO userDAO, FilterDAO filterDAO, CrimeDAO crimeDAO, CategoryDAO categoryDAO) {
        this.userDAO = userDAO;
        this.filterDAO = filterDAO;
        this.crimeDAO = crimeDAO;
        this.categoryDAO = categoryDAO;
    }
}
