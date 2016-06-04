package ua.in.zloch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.in.zloch.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
