package com.spring.controller;

import com.spring.ServerApplication;
import com.spring.dataprovider.PropertyReader;
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

    private static final Product testProduct1 = new Product();
    @Autowired
    private ProductController productController;

    @BeforeAll
    static void setUp() {
        PropertyReader.getInstance().initTestProperties();

        testProduct1.setName("Test Produkt 1");
        testProduct1.setCategory(Product.Category.FRUIT);
        testProduct1.setPackageGram(100);
        testProduct1.setKCal(100);
        testProduct1.setCarbohydrates(10);
        testProduct1.setProtein(10);
        testProduct1.setFat(10);
        testProduct1.setUnit(Product.Unit.PIECES);
    }

    @AfterAll
    static void tearDown() {
        PropertyReader.getInstance().removeTestProperties();
    }

    @Test
    @Order(1)
    void addProductElement() {
        ResponseEntity<Product> response = productController.addProductElement(testProduct1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testProduct1, response.getBody());
    }

    @Test
    @Order(2)
    void getProductElement() {
        ResponseEntity<Product> response = productController.getProductElement(testProduct1.getName());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testProduct1, response.getBody());
    }

    @Test
    @Order(3)
    void getAllProductElements() {
        ResponseEntity<List<Product>> response = productController.getAllProductElements();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(Objects.requireNonNull(response.getBody()).contains(testProduct1));
    }

    @Test
    @Order(4)
    void updateStockElement() {
        testProduct1.setKCal(150);
        ResponseEntity<Product> response = productController.updateProductElement(testProduct1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testProduct1, response.getBody());
    }

    @Test
    @Order(5)
    void removeProductElement() {
        ResponseEntity<Product> response = productController.removeProduct(testProduct1.getName());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testProduct1, response.getBody());
    }
}
