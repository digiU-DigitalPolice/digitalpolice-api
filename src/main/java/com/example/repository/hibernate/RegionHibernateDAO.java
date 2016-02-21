package com.example.repository.hibernate;

import com.example.entity.Region;
import com.example.repository.HibernateDAO;
import com.example.repository.definition.RegionDAO;
import org.springframework.stereotype.Repository;

@Repository
public class RegionHibernateDAO extends HibernateDAO<Region,Long> implements RegionDAO{
}
