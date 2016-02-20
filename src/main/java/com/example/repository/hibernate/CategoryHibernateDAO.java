package com.example.repository.hibernate;

import com.example.entity.Category;
import com.example.repository.HibernateDAO;
import com.example.repository.definition.CategoryDAO;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryHibernateDAO extends HibernateDAO<Category, Long> implements CategoryDAO {
}
