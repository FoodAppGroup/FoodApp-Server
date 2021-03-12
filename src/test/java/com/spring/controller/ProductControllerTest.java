package com.spring.controller;

import com.spring.ServerApplication;
import com.spring.dataprovider.PropertyReader;
import com.spring.logging.Console;
import com.spring.model.entity.Product;
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
class ProductControllerTest {

    private static final Product testProduct = new Product();

    @Autowired
    private ProductController productController;

    @BeforeAll
    static void setUp() {
        PropertyReader.getInstance().initTestProperties();

        testProduct.setName("Test Produkt");
        testProduct.setCategory(Product.Category.FRUIT);
        testProduct.setPackageGram(100);
        testProduct.setKCal(100);
        testProduct.setCarbohydrates(10);
        testProduct.setProtein(10);
        testProduct.setFat(10);
        testProduct.setUnit(Product.Unit.PIECES);

        Console.log("TEST INFO", testProduct.toString());
    }

    @AfterAll
    static void tearDown() {
        Console.log("TEST INFO", testProduct.toString());
        PropertyReader.getInstance().removeTestProperties();
    }

    @Test
    @Order(1)
    void addElement() {
        ResponseEntity<Product> response = productController.addElement(testProduct);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testProduct, response.getBody());
    }

    @Test
    @Order(2)
    void getElement() {
        ResponseEntity<Product> response = productController.getElement(testProduct.getName());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testProduct, response.getBody());
    }

    @Test
    @Order(3)
    void getAllElements() {
        ResponseEntity<List<Product>> response = productController.getAllElements();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(Objects.requireNonNull(response.getBody()).contains(testProduct));
    }

    @Test
    @Order(4)
    void updateElement() {
        testProduct.setKCal(150);
        ResponseEntity<Product> response = productController.updateElement(testProduct);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testProduct, response.getBody());
    }

    @Test
    @Order(5)
    void removeElement() {
        ResponseEntity<Product> response = productController.removeElement(testProduct.getName());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testProduct, response.getBody());
    }
}
