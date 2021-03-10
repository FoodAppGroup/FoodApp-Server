package com.spring.database.repository;

import com.spring.model.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Stock.Key> {
}
