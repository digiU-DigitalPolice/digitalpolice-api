package com.example.repository.hibernate;

import com.example.entity.Filter;
import com.example.repository.HibernateDAO;
import com.example.repository.definition.FilterDAO;
import org.springframework.stereotype.Repository;

@Repository
public class FilterHibernateDAO extends HibernateDAO<Filter,Long> implements FilterDAO {
}
