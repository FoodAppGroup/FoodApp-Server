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
@Entity(name = "Stock")
@Table(name = "stock")
public class Stock implements Serializable {

    @EmbeddedId
    private Key key;

    @ApiModelProperty(value = "Number of the amount in the stock.", example = "2")
    @Column(name = "number", nullable = false)
    private Integer number;

    @MapsId("productName")
    @OneToOne(targetEntity = Product.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_name", referencedColumnName = "name", nullable = false, unique = true, updatable = false)
    private Product product;

    //==================================================================================================================

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class Key implements Serializable {

        @Basic
        @ApiModelProperty(value = "Link to a product.", example = "Apfel")
        private String productName;
    }
}
