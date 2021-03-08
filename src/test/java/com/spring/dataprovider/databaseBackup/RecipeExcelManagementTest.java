package com.spring.dataprovider.databaseBackup;

import com.spring.dataprovider.PropertyReader;
import com.spring.model.entity.RecipeEntity;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RecipeExcelManagementTest {

    private static final RecipeEntity recipe = new RecipeEntity();
    private static final List<RecipeEntity> list = new ArrayList<>();
    private static RecipeExcelManagement excelManagement;

    @BeforeAll
    static void setUp() {
        PropertyReader.getInstance().initTestProperties();
        excelManagement = new RecipeExcelManagement();

        recipe.setName("Nudeln kochen");
        recipe.setDescription("Nudeln kochen und warten");
        recipe.setKCal(811);
        recipe.setCarbohydrates(88);
        recipe.setProtein(11);
        recipe.setFat(7);

        list.add(recipe);
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
        List<RecipeEntity> result = excelManagement.readTable();
        assertEquals(list.get(0), result.get(0));
    }

    @Test
    @Order(3)
    void removeFile() {
        if (PropertyReader.getInstance().getTest_DeleteTempFiles()) {
            File file = new File(PropertyReader.getInstance().getExcel_RecipeTablePath());
            assertTrue(file.delete());
        }
    }
}
