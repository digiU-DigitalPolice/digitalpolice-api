package ua.in.zloch.repository.hibernate;

import org.hibernate.criterion.Restrictions;
import ua.in.zloch.entity.Region;
import ua.in.zloch.repository.HibernateDAO;
import ua.in.zloch.repository.definition.RegionDAO;
import org.springframework.stereotype.Repository;
import org.hibernate.Criteria;
import java.util.List;

@Repository
public class RegionHibernateDAO extends HibernateDAO<Region,Long> implements RegionDAO {
    public List<Region> searchByKoatuu(String koatuu) {
        Criteria criteria = getSession().createCriteria(Region.class);
        if(!koatuu.isEmpty())
            criteria.add(Restrictions.eq("koatuu", koatuu));

        return criteria.list();
    }

}
