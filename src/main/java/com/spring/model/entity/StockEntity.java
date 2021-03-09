package com.spring.model.entity;

import com.spring.model.Product;
import com.spring.model.entity.compositeKey.StockKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Stock")
@Table(name = "stock")
public class StockEntity implements Serializable {

    @EmbeddedId
    private StockKey key;

    @Column(name = "number", nullable = false)
    private Integer number;

    @ManyToOne
    @MapsId("productName")
    @JoinColumn(name = "product_name")
    private Product product;
}
