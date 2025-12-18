package app.elecSpace.repository;

import app.elecSpace.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> { }
