package com.spring.dataprovider;

import com.spring.logging.Console;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private static PropertyReader INSTANCE;
    private final Properties properties;

    private PropertyReader() {
        properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("file.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropertyReader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PropertyReader();
        }
        return INSTANCE;
    }

    public void initTestProperties() {
        properties.forEach((key, value) -> {
            if (value.toString().contains("main")) {
                properties.setProperty(key.toString(), value.toString().replace("main", "test"));
            }
        });
        Console.log("PROPERTY INFO", "Initialized Test Properties: main -> test");
    }

    public void removeTestProperties() {
        properties.forEach((key, value) -> {
            if (value.toString().contains("test")) {
                properties.setProperty(key.toString(), value.toString().replace("test", "main"));
            }
        });
        Console.log("PROPERTY INFO", "Removed Test Properties: test -> main");
    }

    public Boolean getConsole_ShowLog() {
        return Boolean.parseBoolean(properties.getProperty("Console_ShowLog"));
    }

    public String getConsole_LogDirectoryPath() {
        return properties.getProperty("Console_LogDirectoryPath");
    }

    public Integer getExcel_ColumnWidth() {
        return Integer.parseInt(properties.getProperty("Excel_ColumnWidth"));
    }

    public String getExcel_ProductTablePath() {
        return properties.getProperty("Excel_ProductTablePath");
    }

    public String getExcel_RecipeTablePath() {
        return properties.getProperty("Excel_RecipeTablePath");
    }

    public String getExcel_RecipePartTablePath() {
        return properties.getProperty("Excel_RecipePartTablePath");
    }

    public String getExcel_RecipePlanningTablePath() {
        return properties.getProperty("Excel_RecipePlanningTablePath");
    }

    public String getExcel_ShoppingListTablePath() {
        return properties.getProperty("Excel_ShoppingListTablePath");
    }

    public String getExcel_StockTablePath() {
        return properties.getProperty("Excel_StockTablePath");
    }

    public Boolean getTest_DeleteTempFiles() {
        return Boolean.parseBoolean(properties.getProperty("Test_DeleteTempFiles"));
    }
}
