package com.spring.database.repository;

import com.spring.model.entity.RecipePlanning;
import com.spring.model.entity.compositeKey.RecipePlanningKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipePlanningRepository extends JpaRepository<RecipePlanning, RecipePlanningKey> {
}
