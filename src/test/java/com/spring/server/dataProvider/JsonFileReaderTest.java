package com.spring.server.dataProvider;

import jsonUtility.JsonFileReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class JsonFileReaderTest {

    @Test
    void read() throws IOException {
        System.out.println(JsonFileReader.readFile("src/test/resources/jsonObjects/Food.json"));
    }
}