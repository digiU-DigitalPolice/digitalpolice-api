package ua.in.zloch.core.repository.custom;

import ua.in.zloch.core.dto.CrimeFilter;
import ua.in.zloch.core.entity.Crime;

import java.util.List;

public interface CrimeRepositoryCustom {
    List<Crime> search(CrimeFilter filter);
}
