package ua.in.zloch.converters;

import ua.in.zloch.dto.CrimeListDTO;
import ua.in.zloch.entity.Crime;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

public class CrimeToCrimeDtoConverter implements Converter<List<Crime>, CrimeListDTO> {
    @Override
    public CrimeListDTO convert(List<Crime> crimeList) {
        CrimeListDTO dtoList = new CrimeListDTO();
        for (Crime crime : crimeList) {
            CrimeListDTO.CrimeDTO dto = new CrimeListDTO().new CrimeDTO();
            dto.setCoordinates(crime.getLongitude(), crime.getLatitude());
            dto.setId(crime.getId());
            dto.setDate(crime.getDate());
            dto.setCategoryId(crime.getCategory().getId());
            if (crime.getRegion() != null) {
                dto.setRegionId(crime.getRegion().getId());
            }

            dtoList.addFeature(dto);
        }
        return dtoList;
    }
}
