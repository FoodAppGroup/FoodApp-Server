package com.spring.controller;

import com.spring.database.RecipePlanningDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RecipePlanningController {

    @Autowired
    private RecipePlanningDatabase recipePlanningDatabase;

    //TODO
}
