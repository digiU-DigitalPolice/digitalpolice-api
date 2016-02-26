package ua.in.zloch.repository.hibernate;

import ua.in.zloch.entity.User;
import ua.in.zloch.repository.HibernateDAO;
import ua.in.zloch.repository.definition.UserDAO;
import org.springframework.stereotype.Repository;

@Repository
public class UserHibernateDAO extends HibernateDAO<User, Long> implements UserDAO {
}
