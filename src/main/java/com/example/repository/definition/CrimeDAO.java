package com.example.repository.definition;

import com.example.entity.Crime;
import com.example.entity.Filter;
import com.example.repository.GenericDAO;

import java.util.List;

public interface CrimeDAO extends GenericDAO<Crime,Long> {
    public List<Crime> search(Filter filter);
}
