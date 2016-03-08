package ua.in.zloch.service;

import ua.in.zloch.entity.Category;
import ua.in.zloch.entity.Crime;
import ua.in.zloch.entity.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.in.zloch.entity.Region;
import ua.in.zloch.repository.definition.*;

import java.awt.geom.Path2D;
import java.util.List;

@Service
public class MapService {
    private CrimeDAO crimeDAO;

    @Autowired
    public MapService(CrimeDAO crimeDAO) {
        this.crimeDAO = crimeDAO;
    }

    public List<Crime> filterCrimes(Filter filter) {
        return crimeDAO.search(filter);
    }
}
