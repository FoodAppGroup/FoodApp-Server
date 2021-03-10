package com.spring.model.entity;

import com.spring.model.entity.compositeKey.ShoppingListKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ShoppingListKey.class)
@Entity(name = "ShoppingList")
@Table(name = "shopping_list")
public class ShoppingList implements Serializable {

    @Id
    @Column(name = "list_name", nullable = false)
    private String listName;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_name", referencedColumnName = "name", nullable = false)
    private Product product;

    @Column(name = "number", nullable = false)
    private Integer number;

    //==================================================================================================================

}


