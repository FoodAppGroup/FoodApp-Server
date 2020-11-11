package com.spring.server.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import foodApp.Database;
import foodApp.objects.Food;
import foodApp.objects.Recipe;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class FoodAppController {

    @ApiOperation("Return a Food-Object by given a name.")
    @RequestMapping(value = "/food",
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
        database.closeConnectionToDB();
        return ResponseEntity.ok(food.toString());
    }

    @ApiOperation("Return a Recipe-Object by given a name.")
    @RequestMapping(value = "/recipe",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getRecipe(@RequestParam(value = "name", defaultValue = "Toast mit Apfel") String name) {
        Database database = new Database();
        Recipe recipe;
        try {
            recipe = database.getRecipe(name);
        } catch (SQLException | JsonProcessingException e) {
            return ResponseEntity.notFound().build();
        }
        database.closeConnectionToDB();
        return ResponseEntity.ok(recipe.toString());
    }


}
