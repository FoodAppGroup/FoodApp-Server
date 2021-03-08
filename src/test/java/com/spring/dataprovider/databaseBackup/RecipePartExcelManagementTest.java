package com.spring.dataprovider.databaseBackup;

import com.spring.dataprovider.PropertyReader;
import com.spring.model.entity.RecipePartEntity;
import com.spring.model.entity.compositeKey.RecipePartKey;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RecipePartExcelManagementTest {

    private static final RecipePartEntity recipePart = new RecipePartEntity();
    private static final List<RecipePartEntity> list = new ArrayList<>();
    private static RecipePartExcelManagement excelManagement;

    @BeforeAll
    static void setUp() {
        PropertyReader.getInstance().initTestProperties();
        excelManagement = new RecipePartExcelManagement();

        recipePart.setKey(new RecipePartKey("Nudeln kochen", "Nudeln"));
        recipePart.setNumber(2);

        list.add(recipePart);
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
        List<RecipePartEntity> result = excelManagement.readTable();
        assertEquals(list.get(0), result.get(0));
    }

    @Test
    @Order(3)
    void removeFile() {
        if (PropertyReader.getInstance().getTest_DeleteTempFiles()) {
            File file = new File(PropertyReader.getInstance().getExcel_RecipePartTablePath());
            assertTrue(file.delete());
        }
    }
}
