package com.spring.database.repository;

import com.spring.model.composite.StockKey;
import com.spring.model.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, StockKey> {
}
