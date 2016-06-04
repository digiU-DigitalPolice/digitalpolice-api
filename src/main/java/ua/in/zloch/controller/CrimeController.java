package ua.in.zloch.controller;

import ua.in.zloch.dto.CrimeListDTO;
import ua.in.zloch.repository.dto.CrimeFilter;
import ua.in.zloch.service.CrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrimeController {

    private CrimeService crimeService;

    @Autowired
    public CrimeController(CrimeService crimeService) {
        this.crimeService = crimeService;
    }

    @RequestMapping("/crimes")
    public CrimeListDTO filterCrimes(CrimeFilter filter){
        return crimeService.filterCrimes(filter);
    }

}
