package com.spring.database;

import com.spring.database.backup.ProductExcelManagement;
import com.spring.database.repository.ProductRepository;
import com.spring.model.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class ProductDatabase {

    @Autowired
    private ProductRepository productRepository;


    public Product getElement(String productName) throws IllegalArgumentException {
        Optional<Product> product = productRepository.findById(productName);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new IllegalArgumentException("Product element not found: '" + productName + "'");
        }
    }

    public List<Product> getAllElements() {
        return productRepository.findAll();
    }

    public Product addElement(Product product) throws IllegalArgumentException {
        return productRepository.save(product);
    }

    public Product updateElement(Product product) throws IllegalArgumentException {
        if (productRepository.existsById(product.getName())) {
            return productRepository.save(product);
        } else {
            throw new IllegalArgumentException("Product does not exist: '" + product.getName() + "'");
        }
    }

    public Product removeElement(String productName) throws IllegalArgumentException {
        Product product = getElement(productName);
        productRepository.delete(product);
        return product;
    }

    //==================================================================================================================

    public List<Product> saveBackup() throws IOException {
        ProductExcelManagement excelManagement = new ProductExcelManagement();
        List<Product> allElements = getAllElements();
        excelManagement.writeTable(allElements);
        return allElements;
    }

    public List<Product> loadBackup() throws IOException {
        ProductExcelManagement excelManagement = new ProductExcelManagement();
        excelManagement.readTable().forEach(this::addElement);
        return getAllElements();
    }
}
