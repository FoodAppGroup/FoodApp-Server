package com.spring.database.repository;

import com.spring.model.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<RecipeEntity, String> {
}
