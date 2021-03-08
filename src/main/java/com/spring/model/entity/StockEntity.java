package com.spring.model.entity;

import com.spring.model.Product;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "Stock")
@Table(name = "stock")
public class StockEntity implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_name", nullable = false, unique = true)
    private Product productName;

    @Column(name = "number", nullable = false)
    private Integer number;

    public StockEntity() {
    }
}
