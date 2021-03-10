package com.spring.database;

import com.spring.database.backup.ProductExcelManagement;
import com.spring.database.repository.ProductRepository;
import com.spring.database.utility.RepoBackup;
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

    public List<Product> saveBackup() throws IOException {
        RepoBackup<Product, ProductRepository, ProductExcelManagement> repoBackup
                = new RepoBackup<>(productRepository, new ProductExcelManagement());
        return repoBackup.saveBackup();
    }

    public List<Product> loadBackup() throws IOException {
        RepoBackup<Product, ProductRepository, ProductExcelManagement> repoBackup
                = new RepoBackup<>(productRepository, new ProductExcelManagement());
        return repoBackup.saveBackup();
    }
}
