package com.tarea.rest.tareaRest.repositories;

import com.tarea.rest.tareaRest.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
