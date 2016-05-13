package ua.in.zloch.repository.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ua.in.zloch.entity.Crime;
import ua.in.zloch.repository.HibernateDAO;
import ua.in.zloch.repository.definition.CrimeDAO;
import ua.in.zloch.repository.dto.CrimeFilter;

@Repository
public class CrimeHibernateDAO extends HibernateDAO<Crime, Long> implements CrimeDAO {
    @Override
    public List<Crime> search(CrimeFilter filter) {
        Criteria criteria = getSession().createCriteria(Crime.class);
        if(filter.getDateFrom() != null)
            criteria.add(Restrictions.ge("date", filter.getDateFrom()));
        if(filter.getDateTo() != null)
            criteria.add(Restrictions.le("date", filter.getDateTo()));
        if(filter.getCategories() != null && filter.getCategories().size() > 0)
            criteria.add(Restrictions.in("category.id", filter.getCategories()));
        if(filter.getRegions() != null && filter.getRegions().size() > 0)
            criteria.add(Restrictions.in("region.koatuu", filter.getRegions()));
        return criteria.list();
    }
}
