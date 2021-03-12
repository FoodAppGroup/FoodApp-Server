package com.spring.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@ApiModel
@Entity(name = "ShoppingList")
@Table(name = "shopping_list")
public class Shopping implements Serializable {

    @EmbeddedId
    private Key key;

    @ApiModelProperty(value = "Linked product.", example = "Apfel")
    @ManyToOne
    @JoinColumn(name = "product_name", referencedColumnName = "name", insertable = false, updatable = false)
    private Product product;

    @ApiModelProperty(value = "Number of the amount in the Shopping List.", example = "2")
    @Column(name = "number", nullable = false)
    private Integer number;

    //==================================================================================================================

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel
    @Embeddable
    public static class Key implements Serializable {

        @ApiModelProperty(value = "List name.", example = "Standardliste")
        @Column(name = "list_name", nullable = false)
        private String listName;

        @ApiModelProperty(value = "Linked product name.", example = "Apfel")
        @Column(name = "product_name", nullable = false)
        private String productName;
    }
}
