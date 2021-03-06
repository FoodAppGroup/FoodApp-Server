package com.spring.model;

import com.spring.model.entity.Category;
import com.spring.model.entity.Unit;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "food")
@ApiModel
public class Food {
    @Id //Primary Key
    @ApiModelProperty(value = "Name of the food.", example = "Apfel")
    private String name;
    @NotNull
    @ApiModelProperty(value = "Category in the store.", example = "FRUIT")
    private Category category;
    @NotNull
    @ApiModelProperty(value = "Gram in one package of the store.", example = "500")
    private int packageGramm;
    @NotNull
    @ApiModelProperty(value = "kCal per 100g", example = "400")
    private int kCal;
    @NotNull
    @ApiModelProperty(value = "Carbohydrates per 100g", example = "20")
    private int carbohydrates;
    @NotNull
    @ApiModelProperty(value = "Protein per 100g", example = "10")
    private int protein;
    @NotNull
    @ApiModelProperty(value = "Fat per 100g", example = "10")
    private int fat;
    @NotNull
    @ApiModelProperty(value = "Unit of the food.", example = "GRAM")
    private Unit unit;

    //Constructor for JPA
    public Food() {
    }

    /*
    ____________________________________________________________________________________________________________________
    Utility
     */

    private Unit selectFoodUnit(String text) {
        if (text.equalsIgnoreCase("gram") || text.equalsIgnoreCase("gramm")) {
            return Unit.GRAM;
        } else {
            return Unit.PIECES;
        }
    }
}
