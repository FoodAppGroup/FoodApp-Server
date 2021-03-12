package com.spring.model.entity;

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
public class Shopping implements Serializable {

    @EmbeddedId
    private ShoppingKey key;

    @ManyToOne
    @JoinColumn(name = "product_name", referencedColumnName = "name", insertable = false, updatable = false)
    private Product product;

    @Column(name = "number", nullable = false)
    private Integer number;

    //==================================================================================================================

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class ShoppingKey implements Serializable {

        @Column(name = "list_name", nullable = false)
        private String listName;
        @Column(name = "product_name", nullable = false)
        private String productName;
    }

}


