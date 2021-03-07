package com.spring.dataprovider;

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
        return Boolean.parseBoolean(properties.getProperty("console_show-log"));
    }

    public String getConsole_LogDirectoryPath() {
        return properties.getProperty("console_log-directory-path");
    }

    public String getExcel_FoodTablePath() {
        return properties.getProperty("excel_food-table-path");
    }
}
