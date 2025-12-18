package app.elecSpace.controller;

import app.elecSpace.entity.Category;
import app.elecSpace.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<Category> getAll() {
        return service.getAll();
    }
    @PostMapping
    public Category addCategory(@RequestBody Category category) {
        return service.addCategory(category);
    }
}
