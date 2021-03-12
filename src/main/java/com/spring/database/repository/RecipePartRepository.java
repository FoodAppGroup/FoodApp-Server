package com.spring.database.repository;

import com.spring.model.entity.RecipePart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipePartRepository extends JpaRepository<RecipePart, RecipePart.Key> {
}
