package com.spring.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@ApiModel
@Entity(name = "Recipe")
@Table(name = "recipe")
public class Recipe {

    @ApiModelProperty(value = "Name of the recipe", example = "Apfelkompott")
    @Id
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ApiModelProperty(value = "Description for the recipe", example = "Anleitung mit Hinweisen.")
    @Column(name = "description", nullable = false)
    private String description;

    @ApiModelProperty(value = "kCal per portion", example = "400")
    @Column(name = "kcal", nullable = false)
    private Integer kCal;

    @ApiModelProperty(value = "Carbohydrates per portion", example = "20")
    @Column(name = "carbohydrates", nullable = false)
    private Integer carbohydrates;

    @ApiModelProperty(value = "Protein per portion", example = "10", position = 5)
    @Column(name = "protein", nullable = false)
    private Integer protein;

    @ApiModelProperty(value = "Fat per portion", example = "10", position = 6)
    @Column(name = "fat", nullable = false)
    private Integer fat;
}
