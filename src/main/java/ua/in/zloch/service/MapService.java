package ua.in.zloch.service;

import ua.in.zloch.entity.Crime;
import ua.in.zloch.entity.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.in.zloch.repository.definition.*;

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
