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
    private int kCal;

    @Column(name = "carbohydrates", nullable = false)
    private int carbohydrates;

    @Column(name = "protein", nullable = false)
    private int protein;

    @Column(name = "fat", nullable = false)
    private int fat;

    @OneToMany(mappedBy = "recipe")
    private Set<RecipePartEntity> recipeParts;


    public RecipeEntity() {
    }
}
