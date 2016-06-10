package ua.in.zloch.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import ua.in.zloch.api.dto.CrimeListDTO;
import ua.in.zloch.core.dto.CrimeFilter;
import ua.in.zloch.core.repository.CrimeRepository;

@Service
public class CrimeService {

    private CrimeRepository crimeRepository;
    private ConversionService conversionService;

    @Autowired
    public CrimeService(CrimeRepository crimeRepository, ConversionService conversionService) {
        this.crimeRepository = crimeRepository;
        this.conversionService = conversionService;
    }

    public CrimeListDTO filterCrimes(CrimeFilter filter) {
        return conversionService.convert(crimeRepository.search(filter), CrimeListDTO.class);
    }
}
