package com.spring.model.entity;

import com.spring.model.Product;
import com.spring.model.entity.compositeKey.ShoppingListKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ShoppingList")
@Table(name = "shopping_list")
public class ShoppingListEntity implements Serializable {

    @EmbeddedId
    private ShoppingListKey key;

    @Column(name = "number", nullable = false)
    private Integer number;

    @ManyToOne
    @MapsId("productName")
    @JoinColumn(name = "product_name")
    private Product product;
}