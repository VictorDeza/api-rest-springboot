package com.tarea.rest.tareaRest.controllers;

import com.tarea.rest.tareaRest.handlers.ModelNotFoundException;
import com.tarea.rest.tareaRest.models.Category;
import com.tarea.rest.tareaRest.models.Product;
import com.tarea.rest.tareaRest.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{category_id}/products")
    public List<Product> findAllByCategory(@PathVariable Long category_id) {
        return categoryService.findAllByCategory(category_id);
    }

    @GetMapping("/{id}")
    public Category getOne(@PathVariable Long id) {
        Optional<Category> category = categoryService.getOne(id);

        if (!category.isPresent()) throw new ModelNotFoundException("id-" + id);

        return category.get();
    }

    @PostMapping
    public ResponseEntity<Category> insert(@RequestBody Category category) {
        return new ResponseEntity<Category>(categoryService.save(category), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@RequestBody Category category, @PathVariable Long id) {
        Optional<Category> updatedCategory = categoryService.getOne(id);

        if (!updatedCategory.isPresent()) return ResponseEntity.notFound().build();

        updatedCategory.get().setName(category.getName());
        updatedCategory.get().setDescription(category.getDescription());

        return new ResponseEntity<Category>(categoryService.save(updatedCategory.get()), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathVariable Long id) {
        Optional<Category> category = categoryService.getOne(id);
        if (!category.isPresent()) return ResponseEntity.notFound().build();
        categoryService.delete(category.get());
        return ResponseEntity.noContent().build();
    }
}
