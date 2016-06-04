package ua.in.zloch.repository.definition;

import ua.in.zloch.entity.Crime;
import ua.in.zloch.repository.GenericDAO;
import ua.in.zloch.repository.dto.CrimeFilter;

import java.util.List;

public interface CrimeDAO extends GenericDAO<Crime,Long> {
    List<Crime> search(CrimeFilter filter);
}
