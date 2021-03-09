package com.spring.database.repository;

import com.spring.model.entity.ShoppingListEntity;
import com.spring.model.entity.compositeKey.ShoppingListKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListRepository extends JpaRepository<ShoppingListEntity, ShoppingListKey> {
}
