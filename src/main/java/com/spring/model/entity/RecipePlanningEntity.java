package com.spring.model.entity;

import com.spring.model.entity.compositeKey.RecipePlanningKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "RecipePlanning")
@Table(name = "recipe_planning")
public class RecipePlanningEntity implements Serializable {

    @EmbeddedId
    private RecipePlanningKey key;

    @ManyToOne
    @MapsId("recipeName")
    @JoinColumn(name = "recipe_name")
    private RecipeEntity recipe;
}
