package com.spring.model.entity;

import com.spring.model.Product;
import com.spring.model.entity.compositeKey.ShoppingListKey;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "ShoppingList")
@Table(name = "shopping_list")
public class ShoppingListEntity implements Serializable {

    @EmbeddedId
    private ShoppingListKey id;

    @Column(name = "number", nullable = false)
    private Integer number;

    @ManyToOne
    @MapsId("productName")
    @JoinColumn(name = "product_name")
    private Product product;


    public ShoppingListEntity() {
    }
}
