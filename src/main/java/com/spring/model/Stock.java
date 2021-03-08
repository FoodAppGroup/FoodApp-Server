package com.spring.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @Column(name = "product_name", unique = true)
    private String productName;

    @Column(name = "number", nullable = false)
    private Integer number;

    public Stock() {
    }
}
