package com.spring.model.entity;

import com.spring.model.Product;
import com.spring.model.entity.compositeKey.RecipePartKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "RecipePart")
@Table(name = "recipe_part")
public class RecipePartEntity implements Serializable {

    @EmbeddedId
    private RecipePartKey key;

    @Column(name = "number", nullable = false)
    private Integer number;

    @ManyToOne
    @MapsId("recipeName")
    @JoinColumn(name = "recipe_name") //JPA Foreign Key
    private RecipeEntity recipe;

    @ManyToOne
    @MapsId("productName")
    @JoinColumn(name = "product_name")
    private Product product;
}
