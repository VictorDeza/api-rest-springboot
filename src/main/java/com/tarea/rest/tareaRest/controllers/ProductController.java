package com.tarea.rest.tareaRest.controllers;

import com.tarea.rest.tareaRest.handlers.ModelNotFoundException;
import com.tarea.rest.tareaRest.models.Product;
import com.tarea.rest.tareaRest.services.CategoryService;
import com.tarea.rest.tareaRest.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable Long id) {
        Optional<Product> product = productService.getOne(id);
        if (!product.isPresent()) throw new ModelNotFoundException("id-" + id);
        return product.get();
    }

    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody Product product) {
        return new ResponseEntity<Product>(productService.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable Long id) {
        Optional<Product> updatedProduct = productService.getOne(id);
        if (!updatedProduct.isPresent()) return ResponseEntity.notFound().build();
        updatedProduct.get().setName(product.getName());
        updatedProduct.get().setPrice(product.getPrice());
        updatedProduct.get().setStock(product.getStock());
        updatedProduct.get().setCategory(product.getCategory());

        return new ResponseEntity<Product>(productService.save(updatedProduct.get()), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable Long id) {
        Optional<Product> product = productService.getOne(id);
        if (!product.isPresent()) return ResponseEntity.notFound().build();
        productService.delete(product.get());
        return ResponseEntity.noContent().build();
    }
}
