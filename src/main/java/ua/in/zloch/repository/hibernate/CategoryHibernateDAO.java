package ua.in.zloch.repository.hibernate;

import ua.in.zloch.entity.Category;
import ua.in.zloch.repository.HibernateDAO;
import ua.in.zloch.repository.definition.CategoryDAO;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryHibernateDAO extends HibernateDAO<Category, Long> implements CategoryDAO {
}
