package ua.in.zloch.api.converters;

import org.springframework.core.convert.converter.Converter;
import ua.in.zloch.api.dto.CrimeListDTO;
import ua.in.zloch.core.entity.Crime;

import java.util.List;

public class CrimeToCrimeDtoConverter implements Converter<List<Crime>, CrimeListDTO> {
    @Override
    public CrimeListDTO convert(List<Crime> crimeList) {
        CrimeListDTO dtoList = new CrimeListDTO();
        for (Crime crime : crimeList) {
            CrimeListDTO.CrimeDTO dto = new CrimeListDTO().new CrimeDTO();
            dto.setCoordinates(crime.getLongitude(), crime.getLatitude());
            dto.setId(crime.getId());
            if(crime.getDate() != null){
                dto.setDate(crime.getDate());
            }
            if(crime.getCategory() != null){
                dto.setCategoryId(crime.getCategory().getId());
            }

            dtoList.addFeature(dto);
        }
        return dtoList;
    }
}
