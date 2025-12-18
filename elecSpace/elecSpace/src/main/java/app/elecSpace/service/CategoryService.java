package app.elecSpace.service;

import app.elecSpace.entity.Category;
import app.elecSpace.repository.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepo repo;

    public CategoryService(CategoryRepo repo) {
        this.repo = repo;
    }

    public List<Category> getAll() {
        return repo.findAll();
    }

    public Category addCategory(Category category) {
        return repo.save(category);
    }
}
