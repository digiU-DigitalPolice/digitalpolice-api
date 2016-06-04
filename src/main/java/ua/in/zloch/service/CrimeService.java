package ua.in.zloch.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import ua.in.zloch.dto.CrimeListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.in.zloch.repository.CrimeRepository;
import ua.in.zloch.dto.CrimeFilter;

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
