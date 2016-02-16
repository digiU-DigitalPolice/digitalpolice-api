package com.example.repository.hibernate;

import com.example.entity.User;
import com.example.repository.HibernateDAO;
import com.example.repository.definition.UserDAO;

public class UserHibernateDAO extends HibernateDAO<User, Long> implements UserDAO{
}
