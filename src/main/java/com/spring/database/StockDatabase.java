package com.spring.database;

import com.spring.database.repository.StockRepository;
import com.spring.model.entity.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StockDatabase {

    @Autowired
    private StockRepository stockRepository;


    public Stock getElement(String productName) throws IllegalArgumentException {
        Optional<Stock> stock = stockRepository.findById(productName);
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
        Stock stockElement = new Stock();
        stockElement.setProductName(productName);
        stockElement.setNumber(number);
        //stockElement.setProduct(productDatabase.getElement(productName)); TODO -> create NullPointer
        stockRepository.saveAndFlush(stockElement);
        return getElement(productName);
    }

    public Stock updateElement(String productName, int number) throws IllegalArgumentException {
        Stock stock = getElement(productName);
        stock.setNumber(number);
        return stockRepository.save(stock);
    }

    public Stock removeElement(String productName) throws IllegalArgumentException {
        Stock stockElement = getElement(productName);
        stockRepository.delete(stockElement);
        return stockElement;
    }
}
