package com.spring.database;

import com.spring.database.repository.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingListDatabase {

    @Autowired
    private ShoppingListRepository shoppingListRepository;


}
