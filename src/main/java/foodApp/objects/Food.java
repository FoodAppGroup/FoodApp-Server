package foodApp.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import foodApp.enums.FoodUnit;
import lombok.Data;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class Food {

    /*
    Variables from the DB
     */
    private String name;
    private int kcal;
    private int carbohydrates;
    private int protein;
    private int fat;
    private FoodUnit unit;
    private int gramPackageSize;

    /*
    Default Constructor with mapping to the Json values.
     */
    public Food(
            @JsonProperty("name") String name,
            @JsonProperty("kcal") int kcal,
            @JsonProperty("carbohydrates") int carbohydrates,
            @JsonProperty("protein") int protein,
            @JsonProperty("fat") int fat,
            @JsonProperty("unit") String unit,
            @JsonProperty("grammPackageSize") int gramPackageSize) {
        this.name = name;
        this.kcal = kcal;
        this.carbohydrates = carbohydrates;
        this.protein = protein;
        this.fat = fat;
        this.unit = selectFoodUnit(unit);
        this.gramPackageSize = gramPackageSize;
    }

    /*
    Constructor with a Json String to get used from REST API.
     */
    public Food(String jsonData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Food tempFood = mapper.readValue(jsonData, Food.class);
        this.name = tempFood.getName();
        this.kcal = tempFood.getKcal();
        this.carbohydrates = tempFood.getCarbohydrates();
        this.protein = tempFood.getCarbohydrates();
        this.fat = tempFood.getFat();
        this.unit = tempFood.getUnit();
        this.gramPackageSize = tempFood.getGramPackageSize();
    }

    /*
    Constructor with a ResultSet to get used from Database.
     */
    public Food(ResultSet resultSet) throws SQLException {
        this.name = resultSet.getString(1);
        this.kcal = resultSet.getInt(2);
        this.carbohydrates = resultSet.getInt(3);
        this.protein = resultSet.getInt(4);
        this.fat = resultSet.getInt(5);
        this.unit = selectFoodUnit(resultSet.getString(6));
        this.gramPackageSize = resultSet.getInt(7);
    }

    /*
    Overrides the complex equals() to a simple equals with only the name.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return this.name.equalsIgnoreCase(food.getName());
    }

    /*
    Still providing the complex equals() method.
     */
    public boolean equalsFull(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return kcal == food.kcal &&
                carbohydrates == food.carbohydrates &&
                protein == food.protein &&
                fat == food.fat &&
                gramPackageSize == food.gramPackageSize &&
                name.equals(food.name) &&
                unit == food.unit;
    }

    /*
    toString() generates a json
     */
    @SneakyThrows
    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    /*
    ____________________________________________________________________________________________________________________
    Utility
     */

    private FoodUnit selectFoodUnit(String text) {
        if (text.equalsIgnoreCase("gram") || text.equalsIgnoreCase("gramm")) {
            return FoodUnit.GRAM;
        } else {
            return FoodUnit.PIECES;
        }
    }
}
