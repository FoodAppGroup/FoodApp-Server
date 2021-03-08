package com.spring.model.entity.compositeKey;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class ShoppingListKey implements Serializable {

    @Column(name = "list_name", nullable = false)
    private String listName;

    @Column(name = "product_name", nullable = false)
    private String productName;

    public ShoppingListKey() {
    }

    public ShoppingListKey(String listName, String productName) {
        this.listName = listName;
        this.productName = productName;
    }
}
