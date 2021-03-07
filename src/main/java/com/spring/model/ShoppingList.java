package com.spring.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "shopping-list")
public class ShoppingList {

    @Id
    @Column(name = "LIST_NAME")
    private String name;

    @NotNull
    @Column(name = "PRODUCT_KEY")
    private String productName;

    @NotNull
    @Column(name = "NUMBER")
    private Integer number;
}
