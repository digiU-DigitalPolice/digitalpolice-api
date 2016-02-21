package com.example.repository.hibernate;

import com.example.entity.Crime;
import com.example.entity.Filter;
import com.example.repository.HibernateDAO;
import com.example.repository.definition.CrimeDAO;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CrimeHibernateDAO extends HibernateDAO<Crime, Long> implements CrimeDAO {
    @Override
    public List<Crime> search(Filter filter) {
        return getSession().createCriteria(Crime.class)
                .add(Restrictions.between("date", filter.getDateFrom(), filter.getDateTo()))
                .add(Restrictions.in("category", filter.getCategories()))
                .add(Restrictions.in("region", filter.getRegions()))
                .list();
    }
}
