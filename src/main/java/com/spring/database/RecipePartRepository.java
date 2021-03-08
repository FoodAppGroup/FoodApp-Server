package com.spring.database;

import com.spring.model.entity.RecipePartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipePartRepository extends JpaRepository<RecipePartEntity, Integer> {
}
