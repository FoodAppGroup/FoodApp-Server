package com.spring.model.entity;

import com.spring.model.Product;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "RecipePart")
@Table(name = "recipe_part")
public class RecipePartEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_name", nullable = false)
    private RecipeEntity recipeName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_name", nullable = false)
    private Product productName;

    @Column(name = "number", nullable = false)
    private int number;

    public RecipePartEntity() {
    }
}
