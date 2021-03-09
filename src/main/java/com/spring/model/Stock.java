package com.spring.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@ApiModel
@Entity(name = "Stock")
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @ApiModelProperty(value = "Product object.", example = "Apfel")
    @OneToOne(targetEntity = Product.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_name_fk", referencedColumnName = "name", nullable = false, unique = true)
    private Product product;

    @ApiModelProperty(value = "Number of the amount in the stock.", example = "2")
    @Column(name = "number", nullable = false)
    private Integer number;
}
