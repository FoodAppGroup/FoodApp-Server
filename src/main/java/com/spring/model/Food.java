package com.spring.model;

import com.spring.model.entity.Category;
import com.spring.model.entity.Unit;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Data // Lombock Data -> getter and setter
@Entity // JPA Entity
@Table(name = "food") // JPA Table
@ApiModel //Swagger Model
public class Food {

    @Id //Primary Key (JPA)
    @ApiModelProperty(value = "Name of the food.", example = "Apfel") //Swagger Model Property
    private String name;

    @NotNull // JPA Not Null
    @Enumerated(EnumType.STRING) // JPA save Enum as string
    @ApiModelProperty(value = "Category in the store.", example = "FRUIT") // Swagger Model Property
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
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(value = "Unit of the food.", example = "GRAM")
    private Unit unit;

    //Constructor for JPA
    public Food() {
    }
}
