package com.tarea.rest.tareaRest.services;

import com.tarea.rest.tareaRest.models.Product;
import com.tarea.rest.tareaRest.repositories.CategoryRepository;
import com.tarea.rest.tareaRest.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> getOne(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        product.setStatus(1);
        product.setCreated_at(new Date());
        product.setUpdated_at(new Date());
        Product save = productRepository.save(product);
        save.setCategory(categoryService.getOne(save.getCategory().getId()).get());
        return save;
    }

    public boolean delete(Product product) {
        boolean status = getOne(product.getId()).isPresent();
        productRepository.delete(product);
        return status;
    }
}
