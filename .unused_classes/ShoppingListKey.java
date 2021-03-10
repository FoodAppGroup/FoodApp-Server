package com.spring.model.entity.compositeKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingListKey implements Serializable {

    private String listName;
    private String product;
}
