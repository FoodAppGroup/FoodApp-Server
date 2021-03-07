package com.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jsonUtility.JsonBuilder;
import lombok.Data;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;

@Data
public class Recipe {

    /*
    Variables from the DB
     */
    private String name;
    private HashMap<String, Integer> parts;
    private String description;

    private int kcal;
    private int carbohydrates;
    private int protein;
    private int fat;

    /*
    Default Constructor with mapping to the Json values.
     */
    public Recipe(
            @JsonProperty("name") String name,
            @JsonProperty("parts") HashMap<String, Integer> parts,
            @JsonProperty("description") String description,
            @JsonProperty("kcal") int kcal,
            @JsonProperty("carbohydrates") int carbohydrates,
            @JsonProperty("protein") int protein,
            @JsonProperty("fat") int fat) {
        this.name = name;
        this.parts = parts;
        this.description = description;
        this.kcal = kcal;
        this.carbohydrates = carbohydrates;
        this.protein = protein;
        this.fat = fat;
    }

    /*
    Constructor with a Json String to get used from REST API.
     */
    public Recipe(String jsonData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Recipe tempRecipe = mapper.readValue(jsonData, Recipe.class);
        this.name = tempRecipe.getName();
        this.parts = tempRecipe.getParts();
        this.description = tempRecipe.getDescription();
        this.kcal = tempRecipe.getKcal();
        this.carbohydrates = tempRecipe.getCarbohydrates();
        this.protein = tempRecipe.getProtein();
        this.fat = tempRecipe.getFat();
    }

    /*
    Constructor with a ResultSet to get used from Database.
     */
    public Recipe(ResultSet resultSet) throws SQLException, JsonProcessingException {
        this.name = resultSet.getString(1);
        this.parts = parseJsonToMap(resultSet.getString(2));
        this.description = resultSet.getString(3);
    }

    /*
    toString() generates a json
     */
    @SneakyThrows
    @Override
    public String toString() {
        return JsonBuilder.build(this);
    }

    /*
    ____________________________________________________________________________________________________________________
    Utility to calculate the nutrition values.
     */

    private HashMap<String, Integer> parseJsonToMap(String jsonData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap<Object, Object> map = mapper.readValue(jsonData, HashMap.class);
        Set<Object> keySet = map.keySet();
        HashMap<String, Integer> parts = new HashMap<>();
        for (Object key : keySet) {
            parts.put((String) key, (Integer) (map.get(key)));
        }
        return parts;
    }

}
