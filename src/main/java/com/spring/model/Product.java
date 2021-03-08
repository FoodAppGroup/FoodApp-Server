package com.spring.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Data // Lombock Data -> getter and setter
@Entity(name = "Product")
@Table(name = "product") // JPA Table
@ApiModel //Swagger Model
public class Product {

    @Id //Primary Key (JPA)
    @Column(name = "name", nullable = false, unique = true)
    @ApiModelProperty(value = "Name of the product.", example = "Apfel") //Swagger Model Property
    private String name;

    @Enumerated(EnumType.STRING) // JPA save Enum as string
    @Column(name = "category", nullable = false)
    @ApiModelProperty(value = "Category in the store.", example = "FRUIT") // Swagger Model Property
    private Category category;

    @Column(name = "package_gram", nullable = false)
    @ApiModelProperty(value = "Gram in one package of the store.", example = "500")
    private int packageGram;

    @Column(name = "kcal", nullable = false)
    @ApiModelProperty(value = "kCal per 100g", example = "400")
    private int kCal;

    @Column(name = "carbohydrates", nullable = false)
    @ApiModelProperty(value = "Carbohydrates per 100g", example = "20")
    private int carbohydrates;

    @Column(name = "protein", nullable = false)
    @ApiModelProperty(value = "Protein per 100g", example = "10")
    private int protein;

    @Column(name = "fat", nullable = false)
    @ApiModelProperty(value = "Fat per 100g", example = "10")
    private int fat;

    @Enumerated(EnumType.STRING)
    @Column(name = "unit", nullable = false)
    @ApiModelProperty(value = "Unit of the product.", example = "GRAM")
    private Unit unit;

    //Constructor for JPA
    public Product() {
    }

    //==================================================================================================================

    public enum Category {
        VEGETABLES, // Translation: Gemüse
        FRUIT, // Translation: Obst
        PASTA; // Translation: Teigwaren

        public static Category getValue(String string) throws IllegalArgumentException {
            for (Category entity : Category.class.getEnumConstants()) {
                if (entity.toString().equalsIgnoreCase(string)) {
                    return entity;
                }
            }
            throw new IllegalArgumentException("No match with a Category-Enum: " + string);
        }
    }

    public enum Unit {
        GRAM,
        PIECES;

        public static Unit getValue(String string) throws IllegalArgumentException {
            for (Unit entity : Unit.class.getEnumConstants()) {
                if (entity.toString().equalsIgnoreCase(string)) {
                    return entity;
                }
            }
            throw new IllegalArgumentException("No match with a Unit-Enum: " + string);
        }
    }
}
