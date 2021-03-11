package com.spring.model.entity;

import com.spring.model.composite.StockKey;
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

    @EmbeddedId
    private StockKey key;

    @ApiModelProperty(value = "Number of the amount in the stock.", example = "2")
    @Column(name = "number", nullable = false)
    private Integer number;

    @MapsId("productName")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_name", nullable = false)
    private Product product;

    //==================================================================================================================

}
