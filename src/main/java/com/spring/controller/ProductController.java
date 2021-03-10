package com.spring.controller;

import com.spring.database.ProductDatabase;
import com.spring.model.entity.Product;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProductController {

    @Autowired
    private ProductDatabase productDatabase;

    @ApiOperation("Request to get a product element by it's name.")
    @RequestMapping(value = "/product/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProductElement(
            @RequestParam(value = "name", defaultValue = "Apfel") String productName) {

        return ResponseEntity.ok(productDatabase.getElement(productName));
    }

    @ApiOperation("Request to get all product elements.")
    @RequestMapping(value = "/product/get-all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getAllProductElements() {

        return ResponseEntity.ok(productDatabase.getAllElements());
    }

    @ApiOperation("Request to add a new stock element.")
    @RequestMapping(value = "/product/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> addProductElement(
            @RequestBody Product product) {

        return ResponseEntity.ok(productDatabase.addElement(product));
    }

    @ApiOperation("Request to update a stock element.")
    @RequestMapping(value = "/product/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> updateProductElement(
            @RequestBody Product product) {

        return ResponseEntity.ok(productDatabase.updateElement(product));
    }

    @ApiOperation("Request to update a stock element.")
    @RequestMapping(value = "/product/remove", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> removeProduct(
            @RequestBody String productName) {

        return ResponseEntity.ok(productDatabase.removeElement(productName));
    }

    @ApiOperation("Update the database with the backup file.")
    @RequestMapping(value = "/product/backup/load", method = RequestMethod.POST)
    public ResponseEntity<List<Product>> loadBackupFromFile() throws IOException {

        return ResponseEntity.ok(productDatabase.loadBackup());
    }

    @ApiOperation("Saves the entire product table to a file.")
    @RequestMapping(value = "/product/backup/save", method = RequestMethod.POST)
    public ResponseEntity<List<Product>> saveBackupToFile() throws IOException {

        return ResponseEntity.ok(productDatabase.saveBackup());
    }
}
