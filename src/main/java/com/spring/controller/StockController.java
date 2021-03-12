package com.spring.controller;

import com.spring.database.StockDatabase;
import com.spring.model.entity.Stock;
import com.spring.model.request.StockRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class StockController {

    @Autowired
    private StockDatabase stockDatabase;

    @ApiOperation("Request to get a stock element by it's name.")
    @RequestMapping(value = "/stock/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Stock> getElement(
            @RequestParam(value = "product name", defaultValue = "Apfel") String productName) {

        return ResponseEntity.ok(stockDatabase.getElement(productName));
    }

    @ApiOperation("Request to get all stock elements.")
    @RequestMapping(value = "/stock/get-all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Stock>> getAllElements() {

        return ResponseEntity.ok(stockDatabase.getAllElements());
    }

    @ApiOperation("Request to add a new stock element.")
    @RequestMapping(value = "/stock/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Stock> addElement(
            @RequestBody StockRequest request) {

        return ResponseEntity.ok(stockDatabase.addElement(request.getProductName(), request.getNumber()));
    }

    @ApiOperation("Request to update a stock element.")
    @RequestMapping(value = "/stock/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Stock> updateElement(
            @RequestBody StockRequest request) {

        return ResponseEntity.ok(stockDatabase.updateElement(request.getProductName(), request.getNumber()));
    }

    @ApiOperation("Request to update a stock element.")
    @RequestMapping(value = "/stock/remove", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Stock> removeElement(
            @RequestParam(value = "product name", defaultValue = "Apfel") String productName) {

        return ResponseEntity.ok(stockDatabase.removeElement(productName));
    }

    @ApiOperation("Update the database with the backup file.")
    @RequestMapping(value = "/stock/backup/load", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Stock>> loadBackup() throws IOException {

        return ResponseEntity.ok(stockDatabase.loadBackup());
    }

    @ApiOperation("Saves the entire table to a file.")
    @RequestMapping(value = "/stock/backup/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Stock>> saveBackup() throws IOException {

        return ResponseEntity.ok(stockDatabase.saveBackup());
    }
}
