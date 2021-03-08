package com.spring.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity(name = "Recipe")
@Table(name = "recipe")
public class RecipeEntity {

    @Id
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "kcal", nullable = false)
    private Integer kCal;

    @Column(name = "carbohydrates", nullable = false)
    private Integer carbohydrates;

    @Column(name = "protein", nullable = false)
    private Integer protein;

    @Column(name = "fat", nullable = false)
    private Integer fat;

    @OneToMany(mappedBy = "recipe")
    private Set<RecipePartEntity> recipeParts;


    public RecipeEntity() {
    }
}
