package ua.in.zloch.api.converters;

import org.springframework.core.convert.converter.Converter;
import ua.in.zloch.api.dto.CrimeSearchParameters;
import ua.in.zloch.api.dto.PointParameter;
import ua.in.zloch.core.dto.CrimeFilter;
import ua.in.zloch.core.dto.Point;

public class CrimeSearchParamsToCrimeFilterConverter implements Converter<CrimeSearchParameters, CrimeFilter> {

    @Override
    public CrimeFilter convert(CrimeSearchParameters crimeSearchParameters) {
        CrimeFilter filter = new CrimeFilter();
        filter.setCategories(crimeSearchParameters.getCategories());
        filter.setDateTo(crimeSearchParameters.getDateTo());
        filter.setDateFrom(crimeSearchParameters.getDateFrom());
        filter.setNorthEast(convertPointParam(crimeSearchParameters.getNorthEast()));
        filter.setSouthWest(convertPointParam(crimeSearchParameters.getSouthWest()));
        return filter;
    }

    private Point convertPointParam(PointParameter pointParameter) {
        return new Point(pointParameter.getLatitude(), pointParameter.getLongitude());
    }
}
