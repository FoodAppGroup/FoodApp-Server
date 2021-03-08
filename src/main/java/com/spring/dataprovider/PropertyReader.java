package com.spring.dataprovider;

import com.spring.model.Product;
import com.spring.model.entity.*;

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

    public Boolean getConsole_ShowLog() {
        return Boolean.parseBoolean(properties.getProperty("Console_ShowLog"));
    }

    public String getConsole_LogDirectoryPath() {
        return properties.getProperty("Console_LogDirectoryPath");
    }

    public Integer getExcel_ColumnWidth() {
        return Integer.parseInt(properties.getProperty("Excel_ColumnWidth"));
    }

    public <T> String getExcel_TablePath(T object) throws IOException {
        Class<?> objectClass = object.getClass();
        if (Product.class.equals(objectClass)) {
            return getExcel_ProductTablePath();
        } else if (RecipeEntity.class.equals(objectClass)) {
            return getExcel_RecipeTablePath();
        } else if (RecipePartEntity.class.equals(objectClass)) {
            return getExcel_RecipePartTablePath();
        } else if (RecipePlanningEntity.class.equals(objectClass)) {
            return getExcel_RecipePlanningTablePath();
        } else if (ShoppingListEntity.class.equals(objectClass)) {
            return getExcel_ShoppingListTablePath();
        } else if (StockEntity.class.equals(objectClass)) {
            return getExcel_StockTablePath();
        } else {
            throw new IOException("No match with 'Excel_TablePath': " + objectClass.getName());
        }
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
}
