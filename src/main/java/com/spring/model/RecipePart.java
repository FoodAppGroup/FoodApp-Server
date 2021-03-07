package com.spring.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "recipe-part")
public class RecipePart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @NotNull
    @Column(name = "RECIPE_KEY")
    private String recipeName;

    @NotNull
    @Column(name = "PRODUCT_KEY")
    private String productName;

    @NotNull
    @Column(name = "NUMBER")
    private int number;

    public RecipePart() {
    }
}
