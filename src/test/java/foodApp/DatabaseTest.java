package foodApp;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertTrue;


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

        testFood121 = new Food("Test121", "Sonstiges", "Sonstiges", 121, 121, 121, 121, 121, 121, 121);
        testFood100 = new Food("Test100", "Sonstiges", "Sonstiges", 100, 100, 100, 100, 100, 100, 100);
        ArrayList parts = new ArrayList();
        parts.add(new RecipePart(testFood121, 10));
        parts.add(new RecipePart(testFood100, 10));
        testRecipe = new Recipe("TestRecipe", "Cook something", parts);

        assertTrue(database.addNewFoodToDB(testFood121));
        assertTrue(database.addNewFoodToDB(testFood100));
        assertTrue(database.addNewRecipeToDB(testRecipe));

    }

    @AfterAll
    static void endTest() {
        assertTrue(database.deleteFoodFromDB("Test121"));
        assertTrue(database.deleteFoodFromDB("Test100"));
        assertTrue(database.deleteRecipeFromDB("TestRecipe"));

        database.closeConnection();
    }

    /*
    Modify
     */
    @Test
    void getSingleRecipe() {
        assertTrue(testRecipe.equals(database.getRecipe("TestRecipe")));
    }/*
    @Test
    void getSingleFood(){
        assertTrue(testFood100.equals(database.getFood("Test100")));
    }
    @Test
    void stockCountByOne(){
        debugValue1 = database.getFood("Test100").getPiecesStock();
        assertTrue(database.increasePiecesStock("Test100"));
        assertEquals(debugValue1 + 1, database.getFood("Test100").getPiecesStock());
        assertTrue(database.decreasePiecesStock("Test100"));
        assertEquals(debugValue1, database.getFood("Test100").getPiecesStock());
    }
    @Test
    void stockCountByDifference(){
        debugValue1 = database.getFood("Test100").getPiecesStock();
        assertTrue(database.editPiecesStock("Test100",10));
        assertEquals(debugValue1 + 10, database.getFood("Test100").getPiecesStock());
        assertTrue(database.editPiecesStock("Test100",-10));
        assertEquals(debugValue1, database.getFood("Test100").getPiecesStock());
    }
    @Test
    void listCountByOne(){
        debugValue1 = database.getFood("Test10").getPiecesList();
        assertTrue(database.increasePiecesList("Test100"));
        assertEquals(debugValue1+1, database.getFood("Test100").getPiecesList());
        assertTrue(database.decreasePiecesList("Test100"));
    }
    @Test
    void listCountByDifference(){
        debugValue1 = database.getFood("Test100").getPiecesList();
        assertTrue(database.editPiecesList("Test100",10));
        assertEquals(debugValue1 + 10, database.getFood("Test100").getPiecesList());
        assertTrue(database.editPiecesList("Test100",-10));
        assertEquals(debugValue1, database.getFood("Test100").getPiecesList());
    }
    @Test
    void resetCounts(){
        assertTrue(database.resetPiecesStock("Test100"));
        assertTrue(database.resetPiecesList("Test100"));
        assertEquals(0,database.getFood("Test100").getPiecesStock());
        assertEquals(0, database.getFood("Test100").getPiecesList());

    }
    @Test
    void recipeRemoveFromStock(){
        debugValue1 = database.getFood("Test 100").getPiecesStock();
        debugValue1 = database.getFood("Test 121").getPiecesStock();
        database.removeRecipePartsFromStock("TestRecipe");
        assertEquals(debugValue1-10, database.getFood("Test100").getPiecesStock());
        assertEquals(debugValue2-10, database.getFood("Test121").getPiecesStock());
    }
    @Test
    void recipeAddToList(){
        debugValue1 = database.getFood("Test 100").getPiecesList();
        debugValue2 = database.getFood("Test 121").getPiecesList();
        database.setRecipePartsOnList("TestRecipe");
        assertEquals(debugValue1 + 10, database.getFood("Test100").getPiecesList());
        assertEquals(debugValue2 + 10, database.getFood("Test121").getPiecesList());
    }
    @Test
    void bigDifferenceCount(){
        assertTrue(database.editPiecesStock("Test100", -1000));
        assertTrue(database.editPiecesList("Test100", -1000));
        assertEquals(0,database.getFood("Test100").getPiecesList());
        assertEquals(0,database.getFood("Test100").getPiecesStock());
    }

*/
}