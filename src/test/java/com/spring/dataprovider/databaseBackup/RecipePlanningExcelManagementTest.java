package com.spring.dataprovider.databaseBackup;

import com.spring.dataprovider.PropertyReader;
import com.spring.model.entity.RecipePlanningEntity;
import com.spring.model.entity.compositeKey.RecipePlanningKey;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RecipePlanningExcelManagementTest {

    private static final RecipePlanningEntity recipePlanning = new RecipePlanningEntity();
    private static final List<RecipePlanningEntity> list = new ArrayList<>();
    private static RecipePlanningExcelManagement excelManagement;

    @BeforeAll
    static void setUp() {
        PropertyReader.getInstance().initTestProperties();
        excelManagement = new RecipePlanningExcelManagement();

        recipePlanning.setKey(new RecipePlanningKey("Nudeln kochen"));

        list.add(recipePlanning);
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
        List<RecipePlanningEntity> result = excelManagement.readTable();
        assertEquals(list.get(0), result.get(0));
    }

    @Test
    @Order(3)
    void removeFile() {
        if (PropertyReader.getInstance().getTest_DeleteTempFiles()) {
            File file = new File(PropertyReader.getInstance().getExcel_RecipePlanningTablePath());
            assertTrue(file.delete());
        }
    }
}
