package com.spring.dataprovider.databaseBackup;

import com.spring.dataprovider.PropertyReader;
import com.spring.model.entity.ShoppingListEntity;
import com.spring.model.entity.compositeKey.ShoppingListKey;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ShoppingListExcelManagementTest {

    private static final ShoppingListEntity shoppingList = new ShoppingListEntity();
    private static final List<ShoppingListEntity> list = new ArrayList<>();
    private static ShoppingListExcelManagement excelManagement;

    @BeforeAll
    static void setUp() {
        PropertyReader.getInstance().initTestProperties();
        excelManagement = new ShoppingListExcelManagement();

        shoppingList.setKey(new ShoppingListKey("testListe", "Nudeln"));
        shoppingList.setNumber(1);

        list.add(shoppingList);
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
        List<ShoppingListEntity> result = excelManagement.readTable();
        assertEquals(list.get(0), result.get(0));
    }

    @Test
    @Order(3)
    void removeFile() {
        if (PropertyReader.getInstance().getTest_DeleteTempFiles()) {
            File file = new File(PropertyReader.getInstance().getExcel_ShoppingListTablePath());
            assertTrue(file.delete());
        }
    }
}
