package com.spring.controller;

import com.spring.database.ShoppingListDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ShoppingListController {

    @Autowired
    private ShoppingListDatabase shoppingListDatabase;

}
