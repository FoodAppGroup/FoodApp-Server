package com.spring.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "RecipePlanning")
@Table(name = "recipe_planning")
public class RecipePlanningEntity implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_name", nullable = false, unique = true)
    private RecipeEntity recipeName;

    public RecipePlanningEntity() {
    }
}
