package com.spring.controller;

import com.spring.database.StockDatabase;
import com.spring.model.entity.Stock;
import com.spring.model.request.StockRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class StockController {

    @Autowired
    private StockDatabase stockDatabase;

    @ApiOperation("Request to get a stock element by it's name.")
    @RequestMapping(value = "/stock/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Stock> getStockElement(
            @RequestParam(value = "name", defaultValue = "Apfel") String productName) {

        return ResponseEntity.ok(stockDatabase.getElement(productName));
    }

    @ApiOperation("Request to get all stock elements.")
    @RequestMapping(value = "/stock/get-all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Stock>> getAllStockElements() {

        return ResponseEntity.ok(stockDatabase.getAllElements());
    }

    @ApiOperation("Request to add a new stock element.")
    @RequestMapping(value = "/stock/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Stock> addStockElement(
            @RequestBody StockRequest request) {

        return ResponseEntity.ok(stockDatabase.addElement(request.getProductName(), request.getNumber()));
    }

    @ApiOperation("Request to update a stock element.")
    @RequestMapping(value = "/stock/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Stock> updateStockElement(
            @RequestBody StockRequest request) {

        return ResponseEntity.ok(stockDatabase.updateElement(request.getProductName(), request.getNumber()));
    }

    @ApiOperation("Request to update a stock element.")
    @RequestMapping(value = "/stock/remove", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Stock> removeStockElement(
            @RequestBody String productName) {

        return ResponseEntity.ok(stockDatabase.removeElement(productName));
    }
}
