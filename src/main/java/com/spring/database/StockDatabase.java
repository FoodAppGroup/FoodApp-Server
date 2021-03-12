package com.spring.database;

import com.spring.database.backup.StockExcelManagement;
import com.spring.database.repository.StockRepository;
import com.spring.model.entity.Product;
import com.spring.model.entity.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class StockDatabase {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductDatabase productDatabase;

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
        Product product = productDatabase.getElement(productName);

        Stock stockElement = new Stock();
        stockElement.setProductName(productName);
        stockElement.setNumber(number);
        stockRepository.saveAndFlush(stockElement);

        stockElement.setProduct(product); //TODO set before saving (creates NullPointer) and return save()
        return stockElement;
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

    //==================================================================================================================

    public List<Stock> saveBackup() throws IOException {
        productDatabase.saveBackup();
        StockExcelManagement excelManagement = new StockExcelManagement();
        List<Stock> allElements = getAllElements();
        excelManagement.writeTable(allElements);
        return allElements;
    }

    public List<Stock> loadBackup() throws IOException {
        productDatabase.loadBackup();
        StockExcelManagement excelManagement = new StockExcelManagement();
        excelManagement.readTable().forEach(
                stock -> addElement(stock.getProductName(), stock.getNumber()));
        return getAllElements();
    }
}
