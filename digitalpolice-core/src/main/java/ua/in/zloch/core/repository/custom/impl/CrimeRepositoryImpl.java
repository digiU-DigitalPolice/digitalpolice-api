package ua.in.zloch.core.repository.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.in.zloch.core.dto.CrimeFilter;
import ua.in.zloch.core.entity.Crime;
import ua.in.zloch.core.repository.custom.CrimeRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CrimeRepositoryImpl
        implements CrimeRepositoryCustom {

    @Autowired
    private EntityManager em;

    @Override
    public List<Crime> search(CrimeFilter filter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Crime> query= cb.createQuery(Crime.class);
        Root<Crime> c = query.from(Crime.class);

        Expression<Long> crimeId = c.get("id");
        Expression<Long> count = cb.count(crimeId);

        Expression<Double> latitude = c.get("latitude");
        Expression<Double> avgLatitude = cb.avg(latitude);

        Expression<Double> longitude = c.get("longitude");
        Expression<Double> avgLongitude = cb.avg(longitude);

        Expression<String> geohash = c.get("geohash");
        Expression<String> geohashSub = cb.substring(geohash, 1, filter.getPrecision());

        Expression<Long> category = c.get("category");

        Predicate latitudeBetween = cb.between(latitude, filter.getSouthWest().getLatitude(), filter.getNorthEast().getLatitude());
        Predicate longitudeBetween = cb.between(longitude, filter.getSouthWest().getLongitude(), filter.getNorthEast().getLongitude());

        List<Selection> selectionList = new ArrayList<Selection>();
        selectionList.add(count.alias("count"));
        selectionList.add(avgLatitude.alias("latitude"));
        selectionList.add(avgLongitude.alias("longitude"));

        List<Expression> groupingList = new ArrayList<Expression>();
        groupingList.add(geohashSub);

        List<Predicate> predicateList = new ArrayList<Predicate>();
        predicateList.add(latitudeBetween);
        predicateList.add(longitudeBetween);

        if (filter.getDateFrom() != null) {
            predicateList.add(cb.greaterThanOrEqualTo(c.<Date>get("date"), filter.getDateFrom()));
        }
        if (filter.getDateTo() != null) {
            predicateList.add(cb.lessThanOrEqualTo(c.<Date>get("date"), filter.getDateTo()));
        }
        if (filter.getCategories() != null && filter.getCategories().size() > 0) {
            predicateList.add(category.in(filter.getCategories()));
            groupingList.add(category);
            selectionList.add(category.alias("category"));
        }

        query.multiselect(selectionList.toArray(new Selection[selectionList.size()]));
        query.where(predicateList.toArray(new Predicate[predicateList.size()]));
        query.groupBy(groupingList.toArray(new Expression[groupingList.size()]));

        return em.createQuery(query).getResultList();
    }
}
