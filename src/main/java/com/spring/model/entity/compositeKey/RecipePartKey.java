package com.spring.model.entity.compositeKey;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class RecipePartKey implements Serializable {

    @Column(name = "recipe_name", nullable = false)
    private String recipeName;

    @Column(name = "product_name", nullable = false)
    private String productName;

    public RecipePartKey() {
    }

    public RecipePartKey(String recipeName, String productName) {
        this.recipeName = recipeName;
        this.productName = productName;
    }
}
