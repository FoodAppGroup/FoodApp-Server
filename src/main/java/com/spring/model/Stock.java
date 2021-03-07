package com.spring.model;

import com.sun.istack.NotNull;
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
    @Column(name = "PRODUCT_KEY")
    private String productName;

    @NotNull
    @Column(name = "NUMBER")
    private Integer number;

    public Stock() {
    }
}
