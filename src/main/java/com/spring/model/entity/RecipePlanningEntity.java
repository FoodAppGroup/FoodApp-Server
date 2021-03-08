package com.spring.model.entity;

import com.spring.model.entity.compositeKey.RecipePlanningKey;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "RecipePlanning")
@Table(name = "recipe_planning")
public class RecipePlanningEntity implements Serializable {

    @EmbeddedId
    private RecipePlanningKey id;

    @ManyToOne
    @MapsId("recipeName")
    @JoinColumn(name = "recipe_name")
    private RecipeEntity recipe;


    public RecipePlanningEntity() {
    }
}
