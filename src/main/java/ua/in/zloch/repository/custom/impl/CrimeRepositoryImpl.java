package ua.in.zloch.repository.custom.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import ua.in.zloch.dto.CrimeFilter;
import ua.in.zloch.entity.Crime;
import ua.in.zloch.repository.custom.CrimeRepositoryCustom;

public class CrimeRepositoryImpl
    implements CrimeRepositoryCustom {

    @Autowired
    private EntityManager em;

    @Override
    public List<Crime> search(CrimeFilter filter) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Crime> query = builder.createQuery(Crime.class);
        Root<Crime> crime = query.from(Crime.class);

        List<Predicate> predicateList = new LinkedList<Predicate>();

        if (filter.getDateFrom() != null)
            predicateList.add(builder.greaterThanOrEqualTo(crime.<Date>get("date"), filter.getDateFrom()));
        if (filter.getDateTo() != null)
            predicateList.add(builder.lessThanOrEqualTo(crime.<Date>get("date"), filter.getDateTo()));
        if (filter.getCategories() != null && filter.getCategories().size() > 0)
            predicateList.add(crime.get("category").get("id").in(filter.getCategories()));
        if (filter.getRegions() != null && filter.getRegions().size() > 0)
            predicateList.add(crime.get("region").get("koatuu").in(filter.getRegions()));

        query.where(predicateList.toArray(new Predicate[predicateList.size()]));

        return em.createQuery(query).getResultList();
    }
}
