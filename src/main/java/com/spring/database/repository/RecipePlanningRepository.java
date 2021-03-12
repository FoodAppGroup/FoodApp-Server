package com.spring.database.repository;

import com.spring.model.entity.RecipePlanning;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipePlanningRepository extends JpaRepository<RecipePlanning, String> {
}
