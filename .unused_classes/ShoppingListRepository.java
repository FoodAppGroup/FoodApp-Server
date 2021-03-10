package com.spring.database.repository;

import com.spring.model.entity.ShoppingList;
import com.spring.model.entity.compositeKey.ShoppingListKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, ShoppingListKey> {
}
