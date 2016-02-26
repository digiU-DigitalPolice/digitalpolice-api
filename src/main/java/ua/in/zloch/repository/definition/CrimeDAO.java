package ua.in.zloch.repository.definition;

import ua.in.zloch.entity.Crime;
import ua.in.zloch.entity.Filter;
import ua.in.zloch.repository.GenericDAO;

import java.util.List;

public interface CrimeDAO extends GenericDAO<Crime,Long> {
    public List<Crime> search(Filter filter);
}
