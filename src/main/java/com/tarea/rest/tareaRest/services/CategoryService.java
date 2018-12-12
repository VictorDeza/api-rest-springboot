package com.tarea.rest.tareaRest.services;

import com.tarea.rest.tareaRest.models.Category;
import com.tarea.rest.tareaRest.models.Product;
import com.tarea.rest.tareaRest.repositories.CategoryRepository;
import com.tarea.rest.tareaRest.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public List<Product> findAllByCategory(Long category_id){
        return categoryRepository.getOne(category_id).getProducts();
    }

    public Optional<Category> getOne(Long id){
        return categoryRepository.findById(id);
    }

    public Category save(Category category){
        category.setStatus(1);
        category.setCreated_at(new Date());
        category.setUpdated_at(new Date());
        return categoryRepository.save(category);
    }

    public boolean delete(Category category){
        boolean status = getOne(category.getId()).isPresent();
        categoryRepository.delete(category);
        return status;
    }
}
