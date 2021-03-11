package com.spring.database.repository;

import com.spring.model.entity.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, ShoppingList.ShoppingListKey> {
}
