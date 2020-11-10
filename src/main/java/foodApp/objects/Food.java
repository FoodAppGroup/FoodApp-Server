package foodApp.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import foodApp.enums.FoodUnit;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.Objects;

@Data
public class Food {

    /*
    Variables from the DB
     */
    private String name;

    private int piecesStock;
    private int piecesList;

    private int kcal;
    private int carbohydrates;
    private int fat;
    private int protein;

    private FoodUnit unit;

    private int grammPackageSize;

    /*
    Constructor
     */
    public Food(@JsonProperty("name") String name,
                @JsonProperty("piecesStock") int piecesStock,
                @JsonProperty("piecesList") int piecesList,
                @JsonProperty("kcal") int kcal,
                @JsonProperty("carbohydrates") int carbohydrates,
                @JsonProperty("fat") int fat,
                @JsonProperty("protein") int protein,
                @JsonProperty("unit") String unit,
                @JsonProperty("grammPackageSize") int grammPackageSize) {
        this.name = name;
        this.piecesStock = piecesStock;
        this.piecesList = piecesList;
        this.kcal = kcal;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
        this.protein = protein;
        this.unit = selectFoodUnit(unit);
        this.grammPackageSize = grammPackageSize;
    }

    public Food(String jsonData) {
        Food tempFood = createFood(jsonData);
        this.name = tempFood.getName();
        this.piecesStock = tempFood.getPiecesStock();
        this.piecesList = tempFood.getPiecesList();
        this.kcal = tempFood.getKcal();
        this.carbohydrates = tempFood.getCarbohydrates();
        this.fat = tempFood.getFat();
        this.protein = tempFood.getCarbohydrates();
        this.unit = tempFood.getUnit();
        this.grammPackageSize = tempFood.getGrammPackageSize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return this.name.equalsIgnoreCase(food.getName());
    }

    public boolean equalsFull(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return piecesStock == food.piecesStock &&
                piecesList == food.piecesList &&
                kcal == food.kcal &&
                carbohydrates == food.carbohydrates &&
                fat == food.fat &&
                protein == food.protein &&
                grammPackageSize == food.grammPackageSize &&
                name.equals(food.name) &&
                unit == food.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, piecesStock, piecesList, kcal, carbohydrates, fat, protein, unit, grammPackageSize);
    }

    @SneakyThrows
    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    @SneakyThrows
    private Food createFood(String jsonData) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonData, Food.class);
    }

    private FoodUnit selectFoodUnit(String text) {
        if (text.equalsIgnoreCase("stk")
                || text.equalsIgnoreCase("stück")
                || text.equalsIgnoreCase("unit")
                || text.equalsIgnoreCase("units")
                || text.equalsIgnoreCase("pieces")
        ) {
            return FoodUnit.PIECES;
        } else {
            return FoodUnit.GRAMM;
        }
    }

}
