package task.nimapInfotech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import task.nimapInfotech.dto.Category;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(@RequestParam(defaultValue = "0") int page) {
        return categoryService.getAllCategories(page);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }
}