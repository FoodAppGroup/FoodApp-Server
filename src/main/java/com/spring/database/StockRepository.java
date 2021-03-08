package com.spring.database;

import com.spring.model.entity.StockEntity;
import com.spring.model.entity.compositeKey.StockKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<StockEntity, StockKey> {
}
