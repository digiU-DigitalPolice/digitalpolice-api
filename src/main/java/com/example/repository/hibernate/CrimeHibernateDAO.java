package com.example.repository.hibernate;

import com.example.entity.Crime;
import com.example.repository.HibernateDAO;
import com.example.repository.definition.CrimeDAO;
import org.springframework.stereotype.Repository;

@Repository
public class CrimeHibernateDAO extends HibernateDAO<Crime, Long> implements CrimeDAO {
}
