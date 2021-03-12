package com.spring.controller;

import com.spring.ServerApplication;
import com.spring.dataprovider.PropertyReader;
import com.spring.logging.Console;
import com.spring.model.entity.Product;
import com.spring.model.entity.Shopping;
import com.spring.model.request.ShoppingRequest;
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
class ShoppingControllerTest {

    private static final Shopping testShopping = new Shopping();

    @Autowired
    private ShoppingController shoppingController;

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

        testShopping.setKey(new Shopping.Key("Test Liste", testProduct.getName()));
        testShopping.setProduct(testProduct);
        testShopping.setNumber(1);

        Console.log("TEST START", testShopping.toString());
    }

    @AfterAll
    static void tearDown() {
        Console.log("TEST END", testShopping.toString());
        PropertyReader.getInstance().removeTestProperties();
    }

    @Test
    @Order(1)
    void addElement() {
        productController.addElement(testShopping.getProduct());
        Console.log("TEST INSERT", testShopping.getProduct().toString());

        ResponseEntity<Shopping> response = shoppingController.addElement(
                new ShoppingRequest(testShopping.getKey().getListName(), testShopping.getKey().getProductName(), testShopping.getNumber()));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testShopping, response.getBody());
    }

    @Test
    @Order(2)
    void getElement() {
        ResponseEntity<Shopping> response = shoppingController.getElement(testShopping.getKey().getListName(), testShopping.getKey().getProductName());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testShopping, response.getBody());
    }

    @Test
    @Order(3)
    void getAllElements() {
        ResponseEntity<List<Shopping>> response = shoppingController.getAllElements();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(Objects.requireNonNull(response.getBody()).contains(testShopping));
    }

    @Test
    @Order(4)
    void getAllElementsWithListName() {
        ResponseEntity<List<Shopping>> response = shoppingController.getAllElementsFromList(testShopping.getKey().getListName());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(Objects.requireNonNull(response.getBody()).contains(testShopping));
    }

    @Test
    @Order(5)
    void updateElement() {
        testShopping.setNumber(15);
        ResponseEntity<Shopping> response = shoppingController.updateShoppingListElement(
                new ShoppingRequest(testShopping.getKey().getListName(), testShopping.getKey().getProductName(), testShopping.getNumber()));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testShopping, response.getBody());
    }

    @Test
    @Order(6)
    void removeElement() {
        ResponseEntity<Shopping> response = shoppingController.removeShoppingListElement(testShopping.getKey().getListName(), testShopping.getKey().getProductName());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testShopping, response.getBody());

        productController.removeElement(testShopping.getKey().getProductName());
        Console.log("TEST REMOVE", testShopping.getProduct().toString());
    }
}
