package com.spring.controller;

import com.spring.ServerApplication;
import com.spring.dataprovider.PropertyReader;
import com.spring.logging.Console;
import com.spring.model.entity.Product;
import com.spring.model.entity.Stock;
import com.spring.model.request.StockRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@AutoConfigureMockMvc
@SpringBootTest(classes = {ServerApplication.class})
@WebAppConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StockControllerTest {

    private static final Stock testStock = new Stock();

    @Autowired
    private StockController stockController;

    @Autowired
    private ProductController productController;

    @BeforeAll
    static void setUp() {
        PropertyReader.getInstance().initTestProperties();

        Product testProduct = new Product();
        testProduct.setName("Test Produkt");
        testProduct.setCategory(Product.Category.FRUIT);
        testProduct.setPackageGram(100);
        testProduct.setKCal(100);
        testProduct.setCarbohydrates(10);
        testProduct.setProtein(10);
        testProduct.setFat(10);
        testProduct.setUnit(Product.Unit.PIECES);

        testStock.setProductName(testProduct.getName());
        testStock.setProduct(testProduct);
        testStock.setNumber(1);

        Console.log("TEST START", testStock.toString());
    }

    @AfterAll
    static void tearDown() {
        Console.log("TEST END", testStock.toString());
        PropertyReader.getInstance().removeTestProperties();
    }

    @Test
    @Order(1)
    void addElement() {
        productController.addElement(testStock.getProduct());
        Console.log("TEST INSERT", testStock.getProduct().toString());

        ResponseEntity<Stock> response = stockController.addElement(new StockRequest(testStock.getProductName(), testStock.getNumber()));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testStock, response.getBody());
    }

    @Test
    @Order(2)
    void getElement() {
        ResponseEntity<Stock> response = stockController.getElement(testStock.getProductName());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testStock, response.getBody());
    }

    @Test
    @Order(3)
    void getAllElements() {
        ResponseEntity<List<Stock>> response = stockController.getAllElements();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(Objects.requireNonNull(response.getBody()).contains(testStock));
    }

    @Test
    @Order(4)
    void updateElement() {
        testStock.setNumber(15);
        ResponseEntity<Stock> response = stockController.updateElement(new StockRequest(testStock.getProductName(), testStock.getNumber()));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testStock, response.getBody());
    }

    @Test
    @Order(5)
    void removeElement() {
        ResponseEntity<Stock> response = stockController.removeElement(testStock.getProductName());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testStock, response.getBody());

        productController.removeElement(testStock.getProductName());
        Console.log("TEST REMOVE", testStock.getProduct().toString());
    }
}
