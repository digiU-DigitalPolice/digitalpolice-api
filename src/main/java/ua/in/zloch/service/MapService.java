package ua.in.zloch.service;

import ua.in.zloch.entity.Crime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.in.zloch.repository.definition.*;
import ua.in.zloch.repository.dto.CrimeFilter;

import java.util.List;

@Service
public class MapService {
    private CrimeDAO crimeDAO;

    @Autowired
    public MapService(CrimeDAO crimeDAO) {
        this.crimeDAO = crimeDAO;
    }

    public List<Crime> filterCrimes(CrimeFilter filter) {
        return crimeDAO.search(filter);
    }
}
