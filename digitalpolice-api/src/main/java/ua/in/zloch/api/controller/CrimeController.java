package ua.in.zloch.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.in.zloch.api.dto.CrimeListDTO;
import ua.in.zloch.api.service.CrimeService;
import ua.in.zloch.core.dto.CrimeFilter;

@RestController
public class CrimeController {

    private CrimeService crimeService;

    @Autowired
    public CrimeController(CrimeService crimeService) {
        this.crimeService = crimeService;
    }

    @RequestMapping("/crimes")
    public CrimeListDTO filterCrimes(CrimeFilter filter) {
        return crimeService.filterCrimes(filter);
    }

}
