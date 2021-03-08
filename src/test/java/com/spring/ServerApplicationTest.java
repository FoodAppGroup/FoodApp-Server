package com.spring;

import com.spring.dataprovider.PropertyReader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServerApplicationTest {

    @BeforeAll
    static void setUp() {
        PropertyReader.getInstance().initTestProperties();
    }

    @Test
    void contextLoads() {
    }

    @AfterAll
    static void tearDown() {
        PropertyReader.getInstance().removeTestProperties();
    }
}
