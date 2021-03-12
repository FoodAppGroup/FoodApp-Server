package com.spring.database;

import com.spring.database.repository.RecipePartRepository;
import com.spring.database.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecipeDatabase {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipePartRepository recipePartRepository;

    @Autowired
    private ProductDatabase productDatabase;

    //TODO
}
