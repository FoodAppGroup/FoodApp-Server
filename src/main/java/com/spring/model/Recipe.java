package com.spring.model;

import jsonUtility.JsonBuilder;
import lombok.Data;
import lombok.SneakyThrows;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "recipe_part", nullable = false)
    private String recipePart;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "kcal", nullable = false)
    private int kCal;

    @Column(name = "carbohydrates", nullable = false)
    private int carbohydrates;

    @Column(name = "protein", nullable = false)
    private int protein;

    @Column(name = "fat", nullable = false)
    private int fat;

    /*
    Default Constructor with mapping to the Json values.

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

    Constructor with a Json String to get used from REST API.

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


    Constructor with a ResultSet to get used from Database.

    public Recipe(ResultSet resultSet) throws SQLException, JsonProcessingException {
        this.name = resultSet.getString(1);
        this.parts = parseJsonToMap(resultSet.getString(2));
        this.description = resultSet.getString(3);
    }
    */
    /*
    toString() generates a json
     */
    @SneakyThrows
    @Override
    public String toString() {
        return JsonBuilder.build(this);
    }

}
