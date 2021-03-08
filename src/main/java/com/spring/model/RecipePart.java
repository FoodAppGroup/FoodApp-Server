package com.spring.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "recipe_part")
public class RecipePart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "recipe_name", nullable = false)
    private String recipeName;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "number", nullable = false)
    private int number;

    public RecipePart() {
    }
}
