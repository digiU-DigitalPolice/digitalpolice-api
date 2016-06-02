package ua.in.zloch.service;

import org.springframework.core.convert.ConversionService;
import ua.in.zloch.dto.CrimeListDTO;
import ua.in.zloch.entity.Crime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.in.zloch.repository.definition.*;
import ua.in.zloch.repository.dto.CrimeFilter;

import java.util.List;

@Service
public class CrimeService {
    private CrimeDAO crimeDAO;
    private ConversionService conversionService;

    @Autowired
    public CrimeService(CrimeDAO crimeDAO, ConversionService conversionService) {
        this.crimeDAO = crimeDAO;
        this.conversionService = conversionService;
    }

    public CrimeListDTO filterCrimes(CrimeFilter filter) {
        return conversionService.convert(crimeDAO.search(filter), CrimeListDTO.class);
    }
}
