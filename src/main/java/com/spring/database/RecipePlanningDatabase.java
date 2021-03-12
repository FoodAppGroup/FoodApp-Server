package com.spring.database;

import com.spring.database.repository.RecipePlanningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecipePlanningDatabase {

    @Autowired
    private RecipePlanningRepository recipePlanningRepository;

    @Autowired
    private RecipeDatabase recipeDatabase;

    //TODO
}
