package foodApp.objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FoodTest {

    private static Food testFood121;
    private static String stringStorage;

    @BeforeAll
    static void startTest() {
        testFood121 = new Food("Test121", 121, 121, 121, 121, "gram", 121);
    }


    @Test
    @Order(1)
    void writeJson() {
        stringStorage = testFood121.toString();
        Assertions.assertNotNull(stringStorage);
    }

    @Test
    @Order(2)
    void readJson() throws JsonProcessingException {
        Food food = new Food(stringStorage);
        Assertions.assertEquals(testFood121, food, "Tested simple equals method.");
        Assertions.assertTrue(food.equalsFull(testFood121), "Tested full equals method.");
    }

}
