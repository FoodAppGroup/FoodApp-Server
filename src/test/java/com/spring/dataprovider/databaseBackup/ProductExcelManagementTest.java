package com.spring.dataprovider.databaseBackup;

import com.spring.dataprovider.PropertyReader;
import com.spring.model.Product;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductExcelManagementTest {

    private static final Product product = new Product();
    private static final List<Product> list = new ArrayList<>();
    private static ProductExcelManagement excelManagement;

    @BeforeAll
    static void setUp() {
        PropertyReader.getInstance().initTestProperties();
        excelManagement = new ProductExcelManagement();

        product.setName("Nudeln");
        product.setCategory(Product.Category.PASTA);
        product.setPackageGram(500);
        product.setKCal(499);
        product.setCarbohydrates(60);
        product.setProtein(7);
        product.setFat(3);
        product.setUnit(Product.Unit.GRAM);

        list.add(product);
    }

    @AfterAll
    static void tearDown() {
        PropertyReader.getInstance().removeTestProperties();
    }

    @Test
    @Order(1)
    void write() throws IOException {
        excelManagement.writeTable(list);
        assertTrue(true);
    }

    @Test
    @Order(2)
    void read() throws IOException {
        List<Product> result = excelManagement.readTable();
        assertEquals(list.get(0), result.get(0));
    }

    @Test
    @Order(3)
    void removeFile() {
        if (PropertyReader.getInstance().getTest_DeleteTempFiles()) {
            File file = new File(PropertyReader.getInstance().getExcel_ProductTablePath());
            assertTrue(file.delete());
        }
    }
}
