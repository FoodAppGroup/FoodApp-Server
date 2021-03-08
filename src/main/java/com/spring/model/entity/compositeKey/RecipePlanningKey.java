package com.spring.model.entity.compositeKey;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class RecipePlanningKey implements Serializable {

    @Column(name = "recipe_name", nullable = false)
    private String recipeName;

    public RecipePlanningKey() {
    }

    public RecipePlanningKey(String recipeName) {
        this.recipeName = recipeName;
    }
}
