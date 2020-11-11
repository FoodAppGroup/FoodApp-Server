package foodApp;


import com.fasterxml.jackson.core.JsonProcessingException;
import foodApp.objects.Food;
import foodApp.objects.Recipe;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;


class DatabaseTest {
    private static Database database;
    private static Food testFood121;
    private static Food testFood100;
    private static Recipe testRecipe;
    private ArrayList<Food> debugList;
    private int debugValue1;
    private int debugValue2;

    @BeforeAll
    static void startTest() {
        database = new Database();
        database.setLoggingLevel(Level.ALL);

        testFood121 = new Food("Test121", 121, 121, 121, 121, "stk", 121);
        testFood100 = new Food("Test100", 100, 100, 100, 100, "stk", 100);
        //testRecipe = new Recipe("TestRecipe", "Cook something", parts);

    }

    @AfterAll
    static void endTest() {
        database.closeConnectionToDB();
    }

    /*
    Modify
     */
    @Test
    void getSingleFood() throws SQLException {
        Food food = database.getFood("Apfel");
        Assertions.assertNotNull(food);
        System.out.println(food.toString());
    }

    @Test
    void getSingleRecipe() throws SQLException, JsonProcessingException {
        Recipe recipe = database.getRecipe("Toast mit Apfel");
        recipe.calcNutrition(database);
        Assertions.assertNotNull(recipe);
        System.out.println(recipe.toString());
    }

}
