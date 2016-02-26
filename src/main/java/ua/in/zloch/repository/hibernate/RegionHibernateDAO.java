package ua.in.zloch.repository.hibernate;

import ua.in.zloch.entity.Region;
import ua.in.zloch.repository.HibernateDAO;
import ua.in.zloch.repository.definition.RegionDAO;
import org.springframework.stereotype.Repository;

@Repository
public class RegionHibernateDAO extends HibernateDAO<Region,Long> implements RegionDAO {
}
