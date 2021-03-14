package com.spring.controller;

import com.spring.database.RecipePlanningDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipe-planning")
public class RecipePlanningController {

    @Autowired
    private RecipePlanningDatabase recipePlanningDatabase;

    //TODO
}
