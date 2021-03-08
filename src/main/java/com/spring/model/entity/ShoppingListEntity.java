package com.spring.model.entity;

import com.spring.model.Product;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "ShoppingList")
@Table(name = "shopping_list")
public class ShoppingListEntity implements Serializable {

    @Id
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_name", nullable = false, unique = true)
    private Product productName;

    @Column(name = "number", nullable = false)
    private Integer number;

    public ShoppingListEntity() {
    }
}
