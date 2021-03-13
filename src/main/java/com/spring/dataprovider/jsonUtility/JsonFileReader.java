package com.spring.dataprovider.jsonUtility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JsonFileReader {

    public static String readFile(String file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            return stringBuilder.toString();
        }
    }
}
