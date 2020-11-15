package com.spring.server.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import foodApp.Database;
import foodApp.objects.Food;
import foodApp.objects.Recipe;
import io.swagger.annotations.ApiOperation;
import jsonUtility.JsonBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class FoodAppController {

    @ApiOperation("Return a Food-Object by given a name.")
    @RequestMapping(value = "/foodApp/food",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getFood(@RequestParam(value = "name", defaultValue = "Apfel") String name) {
        Database database = new Database();
        Food food;
        try {
            food = database.getFood(name);
        } catch (SQLException e) {
            return ResponseEntity.notFound().build();
        }
        database.disconnectionFromDB();
        return ResponseEntity.ok(food.toString());
    }

    @ApiOperation("Return a Recipe-Object by given a name.")
    @RequestMapping(value = "/foodApp/recipe",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getRecipe(@RequestParam(value = "name", defaultValue = "Toast mit Apfel") String name) {
        Database database = new Database();
        Recipe recipe;
        try {
            recipe = database.getRecipe(name);
            database.disconnectionFromDB();
            return ResponseEntity.ok(recipe.toString());
        } catch (SQLException | JsonProcessingException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation("Return all Food-Objects from the Database.")
    @RequestMapping(value = "/foodApp/all/food",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllFoods() {
        Database database = new Database();
        ArrayList<Food> foods;
        try {
            foods = database.getAllFoods();
            database.disconnectionFromDB();
            return ResponseEntity.ok(JsonBuilder.build(foods));
        } catch (SQLException | JsonProcessingException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation("Return all Food-Objects from the Database.")
    @RequestMapping(value = "/foodApp/all/recipe",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllRecipes() {
        Database database = new Database();
        ArrayList<Recipe> recipes;
        try {
            recipes = database.getAllRecipes();
            database.disconnectionFromDB();
            return ResponseEntity.ok(JsonBuilder.build(recipes));
        } catch (SQLException | JsonProcessingException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
