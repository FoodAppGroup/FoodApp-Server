package com.spring.database;

import com.spring.model.entity.RecipePlanningEntity;
import com.spring.model.entity.compositeKey.RecipePlanningKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipePlanningRepository extends JpaRepository<RecipePlanningEntity, RecipePlanningKey> {
}
