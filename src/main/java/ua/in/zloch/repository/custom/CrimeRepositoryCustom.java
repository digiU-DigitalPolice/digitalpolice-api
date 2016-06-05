package ua.in.zloch.repository.custom;

import ua.in.zloch.dto.CrimeFilter;
import ua.in.zloch.entity.Crime;

import java.util.List;

public interface CrimeRepositoryCustom {
    List<Crime> search(CrimeFilter filter);
}
