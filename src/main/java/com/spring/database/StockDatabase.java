package com.spring.database;

import com.spring.database.repository.StockRepository;
import com.spring.model.entity.Product;
import com.spring.model.entity.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StockDatabase {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductDatabase productDatabase;

    public Stock getElement(String productName) throws IllegalArgumentException {
        Optional<Stock> stock = stockRepository.findById(new Stock.Key(productName));
        if (stock.isPresent()) {
            return stock.get();
        } else {
            throw new IllegalArgumentException("Stock element not found: '" + productName + "'");
        }
    }

    public List<Stock> getAllElements() {
        return stockRepository.findAll();
    }

    public Stock addElement(String productName, int number) throws IllegalArgumentException {
        Product product = productDatabase.getElement(productName);
        Stock stockElement = new Stock();
        stockElement.setKey(new Stock.Key(productName));
        stockElement.setNumber(number);
        stockElement.setProduct(product);

        return stockRepository.save(stockElement);
    }

    public Stock updateElement(String productName, int number) throws IllegalArgumentException {
        Stock stock = getElement(productName);
        stock.setNumber(number);
        return stockRepository.save(stock);
    }
}
