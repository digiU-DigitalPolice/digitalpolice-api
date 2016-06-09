package ua.in.zloch.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.in.zloch.core.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
