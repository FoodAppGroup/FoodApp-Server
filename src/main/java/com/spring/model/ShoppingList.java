package com.spring.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "shopping_list")
public class ShoppingList {

    @Id
    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "number", nullable = false)
    private Integer number;

    public ShoppingList() {
    }
}
