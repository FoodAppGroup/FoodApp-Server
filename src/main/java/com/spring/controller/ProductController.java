package com.spring.controller;

import com.spring.controller.utility.RepoBackup;
import com.spring.controller.utility.RepoLog;
import com.spring.database.backup.ProductExcelManagement;
import com.spring.database.repository.ProductRepository;
import com.spring.model.Product;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @ApiOperation("Request to get a product by it's name.")
    @RequestMapping(value = "/product/get",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@RequestParam(value = "name", defaultValue = "Apfel") String name) {
        return productRepository.findById(name).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @ApiOperation("Request to get all products.")
    @RequestMapping(value = "/product/get-all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }


    @ApiOperation("Request to add a new product.")
    @RequestMapping(value = "/product/add",
            method = RequestMethod.POST)
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        RepoLog<ProductRepository> repoLog = new RepoLog<>(productRepository);
        try {
            productRepository.save(product);
            return ResponseEntity.ok(repoLog.getChangedSize());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Request to update/add a product.")
    @RequestMapping(value = "/product/update",
            method = RequestMethod.POST)
    public ResponseEntity<String> updateProduct(@RequestBody Product product) {
        RepoLog<ProductRepository> repoLog = new RepoLog<>(productRepository);
        try {
            productRepository.save(product);
            return ResponseEntity.ok(repoLog.getChangedSize());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Request to delete a product.")
    @RequestMapping(value = "/product/delete",
            method = RequestMethod.POST)
    public ResponseEntity<String> deleteProduct(@RequestParam(value = "name", defaultValue = "Apfel") String name) {
        RepoLog<ProductRepository> repoLog = new RepoLog<>(productRepository);
        try {
            Optional<Product> productToDelete = productRepository.findById(name);
            if (productToDelete.isPresent()) {
                productRepository.delete(productToDelete.get());
            } else {
                throw new IllegalArgumentException("No match with name in ProductRepository.");
            }
            return ResponseEntity.ok(repoLog.getChangedSize());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //=============== BACKUP ===========================================================================================

    @ApiOperation("Update the database with the backup file.")
    @RequestMapping(value = "/product/backup/load",
            method = RequestMethod.POST)
    public ResponseEntity<String> loadBackupFromFile() {
        try {
            RepoBackup<Product, ProductRepository, ProductExcelManagement> repoBackup
                    = new RepoBackup<>(productRepository, new ProductExcelManagement());
            return ResponseEntity.ok(repoBackup.loadBackup());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Saves the entire product table to a file.")
    @RequestMapping(value = "/product/backup/save",
            method = RequestMethod.POST)
    public ResponseEntity<String> saveBackupToFile() {
        try {
            RepoBackup<Product, ProductRepository, ProductExcelManagement> repoBackup
                    = new RepoBackup<>(productRepository, new ProductExcelManagement());
            return ResponseEntity.ok(repoBackup.saveBackup());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
