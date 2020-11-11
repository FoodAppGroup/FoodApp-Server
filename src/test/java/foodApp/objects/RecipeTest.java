package foodApp.objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.*;

import java.util.HashMap;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RecipeTest {

    private static Recipe testRecipe;
    private static String stringStorage;

    @BeforeAll
    static void startTest() {
        HashMap<String, Integer> parts = new HashMap<>();
        parts.put("Toast", 3);
        parts.put("Apfel", 2);
        testRecipe = new Recipe("TestRecipe", parts, "Test for Recipes", 123, 123, 123, 123);
    }

    @Test
    @Order(1)
    void writeJson() {
        stringStorage = testRecipe.toString();
        Assertions.assertNotNull(stringStorage);
        System.out.println(stringStorage);
    }

    @Test
    @Order(2)
    void readJson() throws JsonProcessingException {
        Recipe tempRecipe = new Recipe(stringStorage);
        Assertions.assertEquals(testRecipe, tempRecipe, "Tested simple equals method.");
        Assertions.assertTrue(tempRecipe.equalsFull(testRecipe), "Tested full equals method.");
    }
}
