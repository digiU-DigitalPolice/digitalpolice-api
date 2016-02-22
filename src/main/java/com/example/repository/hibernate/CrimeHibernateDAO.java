package com.example.repository.hibernate;

import com.example.entity.Crime;
import com.example.entity.Filter;
import com.example.repository.HibernateDAO;
import com.example.repository.definition.CrimeDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CrimeHibernateDAO extends HibernateDAO<Crime, Long> implements CrimeDAO {
    @Override
    public List<Crime> search(Filter filter) {
        Criteria criteria = getSession().createCriteria(Crime.class);
        if(filter.getDateFrom() != null)
            criteria.add(Restrictions.ge("date", filter.getDateFrom()));
        if(filter.getDateTo() != null)
            criteria.add(Restrictions.le("date", filter.getDateTo()));
        if(filter.getCategories() != null && filter.getCategories().size() > 0)
            criteria.add(Restrictions.in("category.id", filter.getCategories()));
        if(filter.getRegions() != null && filter.getRegions().size() > 0)
            criteria.add(Restrictions.in("region", filter.getRegions()));
        return criteria.list();
    }
}
