package com.spring.model.entity.compositeKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class StockKey implements Serializable {

    @Column(name = "product_name", nullable = false)
    private String productName;
}
