package ua.in.zloch.repository.hibernate;

import org.hibernate.criterion.Restrictions;
import ua.in.zloch.entity.Region;
import ua.in.zloch.entity.RegionFilter;
import ua.in.zloch.repository.HibernateDAO;
import ua.in.zloch.repository.definition.RegionDAO;
import org.springframework.stereotype.Repository;
import org.hibernate.Criteria;
import java.util.List;

@Repository
public class RegionHibernateDAO extends HibernateDAO<Region,Long> implements RegionDAO {
    public List<Region> search(RegionFilter filter) {
        Criteria criteria = getSession().createCriteria(Region.class);
        if(filter.getKoatuuIds() != null)
            criteria.add(Restrictions.in("koatuu", filter.getKoatuuIds()));
        return criteria.list();
    }


}
