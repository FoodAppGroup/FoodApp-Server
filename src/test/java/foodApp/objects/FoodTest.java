package foodApp.objects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;


public class FoodTest {

    private static Food testFood121;
    private static String storage;

    @BeforeAll
    static void startTest() {
        testFood121 = new Food("Test121", 121, 121, 121, 121, 121, 121, "stk", 121);
    }


    @Test
    @Order(2)
    void jsonMethods() {
        storage = testFood121.toString();
        Food tempFood = new Food(storage);

        Assertions.assertEquals(testFood121, tempFood, "Tested simple equals method.");
        Assertions.assertTrue(tempFood.equalsFull(testFood121), "Tested full equals method.");
    }

}
