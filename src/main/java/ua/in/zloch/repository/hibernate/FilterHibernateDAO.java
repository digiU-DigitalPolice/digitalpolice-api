package ua.in.zloch.repository.hibernate;

import ua.in.zloch.entity.Filter;
import ua.in.zloch.repository.HibernateDAO;
import ua.in.zloch.repository.definition.FilterDAO;
import org.springframework.stereotype.Repository;

@Repository
public class FilterHibernateDAO extends HibernateDAO<Filter,Long> implements FilterDAO {
}
