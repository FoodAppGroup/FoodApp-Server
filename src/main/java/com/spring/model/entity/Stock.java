package com.spring.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(value = "Linked product name.", example = "Apfel")
    @Id
    @Column(name = "product_name", nullable = false)
    private String productName;

    @ApiModelProperty(value = "Reference to the product.", example = "Apfel")
    //@MapsId //TODO -> will create the table with a foreign key, but crash the repository
    @OneToOne
    @JoinColumn(name = "product_name", referencedColumnName = "name", insertable = false, updatable = false)
    private Product product;

    @ApiModelProperty(value = "Number of the amount in the stock.", example = "2")
    @Column(name = "number", nullable = false)
    private Integer number;
}
