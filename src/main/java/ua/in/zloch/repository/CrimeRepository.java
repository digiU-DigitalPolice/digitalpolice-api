package ua.in.zloch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.in.zloch.entity.Crime;
import ua.in.zloch.repository.custom.CrimeRepositoryCustom;

public interface CrimeRepository extends JpaRepository<Crime, Long>, CrimeRepositoryCustom {}
