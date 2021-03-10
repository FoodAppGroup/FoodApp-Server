package com.spring.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@ApiModel
@Entity(name = "Product")
@Table(name = "product")
public class Product {

    @ApiModelProperty(value = "Name of the product.", example = "Apfel")
    @Id
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ApiModelProperty(value = "Category in the store.", example = "FRUIT")
    @Enumerated(EnumType.STRING) // JPA save Enum as string
    @Column(name = "category", nullable = false)
    private Category category;

    @ApiModelProperty(value = "Gram in one package of the store.", example = "500")
    @Column(name = "package_gram", nullable = false)
    private Integer packageGram;

    @ApiModelProperty(value = "kCal per 100g", example = "400")
    @Column(name = "kcal", nullable = false)
    private Integer kCal;

    @ApiModelProperty(value = "Carbohydrates per 100g", example = "20")
    @Column(name = "carbohydrates", nullable = false)
    private Integer carbohydrates;

    @ApiModelProperty(value = "Protein per 100g", example = "10")
    @Column(name = "protein", nullable = false)
    private Integer protein;

    @ApiModelProperty(value = "Fat per 100g", example = "10")
    @Column(name = "fat", nullable = false)
    private Integer fat;

    @ApiModelProperty(value = "Unit of the product.", example = "GRAM")
    @Enumerated(EnumType.STRING)
    @Column(name = "unit", nullable = false)
    private Unit unit;

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
