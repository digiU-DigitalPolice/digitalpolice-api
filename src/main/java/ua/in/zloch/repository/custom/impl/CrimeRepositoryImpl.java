package ua.in.zloch.repository.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.in.zloch.dto.CrimeFilter;
import ua.in.zloch.entity.Crime;
import ua.in.zloch.repository.custom.CrimeRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public class CrimeRepositoryImpl implements CrimeRepositoryCustom {

    @Autowired
    private EntityManager em;

    @Override
    public List<Crime> search(CrimeFilter filter) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Crime> query = builder.createQuery(Crime.class);
        Root<Crime> crime = query.from(Crime.class);

        if(filter.getDateFrom() != null)
            query.where(builder.greaterThanOrEqualTo(crime.<Date>get("date"), filter.getDateFrom()));
        if(filter.getDateTo() != null)
            query.where(builder.lessThanOrEqualTo(crime.<Date>get("date"), filter.getDateTo()));
        if(filter.getCategories() != null && filter.getCategories().size() > 0)
            query.where(crime.get("category").get("id").in(filter.getCategories()));
        if(filter.getRegions() != null && filter.getRegions().size() > 0)
            query.where(crime.get("region").get("koatuu").in(filter.getRegions()));
        return em.createQuery(query).getResultList();
    }
}
