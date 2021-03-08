package com.spring.model.entity;

import com.spring.model.Product;
import com.spring.model.entity.compositeKey.RecipePartKey;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "RecipePart")
@Table(name = "recipe_part")
public class RecipePartEntity implements Serializable {

    @EmbeddedId
    private RecipePartKey id;

    @Column(name = "number", nullable = false)
    private int number;

    @ManyToOne
    @MapsId("recipeName")
    @JoinColumn(name = "recipe_name") //JPA Foreign Key
    private RecipeEntity recipe;

    @ManyToOne
    @MapsId("productName")
    @JoinColumn(name = "product_name")
    private Product product;


    public RecipePartEntity() {
    }
}
