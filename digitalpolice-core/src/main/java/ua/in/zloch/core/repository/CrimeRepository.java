package ua.in.zloch.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.in.zloch.core.entity.Crime;
import ua.in.zloch.core.repository.custom.CrimeRepositoryCustom;

public interface CrimeRepository extends JpaRepository<Crime, Long>, CrimeRepositoryCustom {
}
