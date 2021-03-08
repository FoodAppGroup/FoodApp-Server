package com.spring.dataprovider.databaseBackup;

import com.spring.dataprovider.PropertyReader;
import com.spring.model.entity.StockEntity;
import com.spring.model.entity.compositeKey.StockKey;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StockExcelManagementTest {

    private static final StockEntity stock = new StockEntity();
    private static final List<StockEntity> list = new ArrayList<>();
    private static StockExcelManagement excelManagement;

    @BeforeAll
    static void setUp() {
        PropertyReader.getInstance().initTestProperties();
        excelManagement = new StockExcelManagement();

        stock.setKey(new StockKey("Nudeln"));
        stock.setNumber(20);

        list.add(stock);
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
        List<StockEntity> result = excelManagement.readTable();
        assertEquals(list.get(0), result.get(0));
    }

    @Test
    @Order(3)
    void removeFile() {
        if (PropertyReader.getInstance().getTest_DeleteTempFiles()) {
            File file = new File(PropertyReader.getInstance().getExcel_StockTablePath());
            assertTrue(file.delete());
        }
    }
}
