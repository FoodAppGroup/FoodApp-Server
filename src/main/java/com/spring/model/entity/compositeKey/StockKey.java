package com.spring.model.entity.compositeKey;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class StockKey implements Serializable {

    @Column(name = "product_name", nullable = false)
    private String productName;

    public StockKey() {
    }

    public StockKey(String productName) {
        this.productName = productName;
    }
}
