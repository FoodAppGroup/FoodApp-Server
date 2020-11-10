package foodApp;

import foodApp.declarations.CategoryList;
import foodApp.declarations.CategoryStock;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    private final String URL = "jdbc:sqlite:./src/main/resources/dataBase/food.sqlite";
    private final String USER = "username";
    private final String PASS = "password";
    private final Connection connection;
    private final Logger logger;
    private Statement statement;

    public Database() {
        connection = connectToDB(URL);
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.setLevel(Level.ALL);
    }

    /*
    DB settings
     */
    public Connection connectToDB(String url) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
            logger.info("Connected to: " + url);
        } catch (SQLException e) {
            //e.printStackTrace();
            logger.severe("Connection to Database failed!");
        } finally {
            return connection;
        }
    }

    public void closeConnection() {
        try {
            connection.close();
            logger.info("Closed connection.");
        } catch (SQLException e) {
            //e.printStackTrace();
            logger.severe("Failure to close the connection to the database!");
        }
    }

    /*
    Get single Object
     */
    public Food getFood(String foodName) {
        String executeString = "SELECT * FROM \"foods_table\" WHERE \"name\" = '" + foodName + "'";
        logger.info(executeString);
        return getFoodFromDB(executeString);
    }

    public Recipe getRecipe(String recipeName) {
        String executeString = "SELECT * FROM \"recipes_table\" WHERE \"name\" = '" + recipeName + "'";
        logger.info(executeString);
        return getRecipeFromDB(executeString);
    }

    /*
    TODO Get Food Lists
     */
    public ArrayList<Food> getAllFoods() {
        return getFoodListFromDB("SELECT * FROM foods_table");
    }

    public ArrayList<Food> getStockCategory(CategoryStock category) {
        return getFoodListFromDB("");
    }

    public ArrayList<Food> getListCategory(CategoryList category) {
        return getFoodListFromDB("");
    }

    public ArrayList<Food> getList() {
        return getFoodListFromDB("");
    }

    /*
    Pieces Stock
     */
    public boolean increasePiecesStock(String foodName) {
        return setPiecesStock(foodName, +1) > 0;
    }

    public boolean decreasePiecesStock(String foodName) {
        return setPiecesStock(foodName, -1) > 0;
    }

    public boolean editPiecesStock(String foodName, int difference) {
        return setPiecesStock(foodName, difference) > 0;
    }

    /*
    Pieces List
     */
    public boolean increasePiecesList(String foodName) {
        return setPiecesList(foodName, +1) > 0;
    }

    public boolean decreasePiecesList(String foodName) {
        return setPiecesList(foodName, -1) > 0;
    }

    public boolean editPiecesList(String foodName, int difference) {
        return setPiecesList(foodName, difference) > 0;
    }

    /*
    Reset Pieces
     */
    public boolean resetPiecesStock(String foodName) {
        String executeString = "UPDATE \"foods_table\" SET \"piecesStock\" = 0 WHERE \"name\" = '" + foodName + "')";
        logger.info(executeString);
        return updateDB(executeString) > 0;
    }

    public boolean resetPiecesList(String foodName) {
        String executeString = "UPDATE \"foods_table\" SET \"piecesList\" = 0 WHERE \"name\" = '" + foodName + "')";
        logger.info(executeString);
        return updateDB(executeString) > 0;
    }

    /*
    Recipe functions
     */
    public boolean setRecipePartsOnList(String recipeName) {
        boolean success = true;
        Recipe recipe = getRecipe(recipeName);
        for (RecipePart part : recipe.getParts()) {
            if (!editPiecesList(part.getFood().getName(), part.getCount())) {
                success = false;
            }
        }
        return success;
    }

    public boolean removeRecipePartsFromStock(String recipeName) {
        boolean success = true;
        Recipe recipe = getRecipe(recipeName);
        for (RecipePart part : recipe.getParts()) {
            if (!editPiecesStock(part.getFood().getName(), -part.getCount())) {
                success = false;
            }
        }
        return success;

    }

    /*
    New Rows in DB
     */

    public boolean addNewFoodToDB(Food food) {
        return addNewFoodToDB(food.getName(), food.getStockCategory().toString(), food.getListCategory().toString(), food.getKcal(), food.getCarbohydrates(), food.getProtein(), food.getFat(), food.getPiecesStock(), food.getPiecesList(), food.getGrammConversionFactor());
    }

    public boolean addNewFoodToDB(String name, String stockCat, String listCat, int kcal, int carbon, int protein, int fat, int piecesStock, int piecesList, int gramm) {
        String executeString = "INSERT INTO \"foods_table\" (\"name\", \"stockCategory\", \"listCategory\", " +
                "\"kcal\", \"carbonhydrates\", \"protein\", \"fat\", \"piecesStock\", \"piecesList\", \"grammConversionFactor\") " +
                "VALUES ('" + name + "', '" + stockCat + "', '" + listCat + "', " + kcal + ", " + carbon + ", " + protein + ", " + fat + ", " + piecesStock + ", " + piecesList + ", " + gramm + ")";
        logger.info(executeString);
        return updateDB(executeString) > 0;
    }

    public boolean addNewRecipeToDB(Recipe recipe) {
        return addNewRecipeToDB(recipe.getName(), recipe.getParts(), recipe.getDescription());
    }

    public boolean addNewRecipeToDB(String name, ArrayList<RecipePart> parts, String description) {
        StringBuilder executeString = new StringBuilder("INSERT INTO \"recipes_table\" ");
        executeString.append("(\"name\", \"description\", \"part1\", \"part1count\", \"part2\", \"part2count\", \"part3\", \"part3count\", \"part4\", \"part4count\", \"part5\", \"part5count\", \"part6\", \"part6count\", \"part7\", \"part7count\", \"part8\", \"part8count\", \"part9\", \"part9count\", \"part10\", \"part10count\") ");
        executeString.append("VALUES (");
        executeString.append("'" + name + "', '" + description + "' ");
        for (int i = 0; i < 10; i++) {
            if (i < parts.size()) {
                executeString.append(", '" + parts.get(i).getFood().getName() + "', '" + parts.get(i).getCount() + "'");
                continue;
            }
            executeString.append(", null, null");
        }
        executeString.append(")");

        logger.info(executeString.toString());
        return updateDB(executeString.toString()) > 0;
    }

    /*
    Delete Rows in DB
     */
    public boolean deleteFoodFromDB(String foodName) {
        String executeString = "DELETE FROM \"foods_table\" WHERE \"name\" = '" + foodName + "'";
        logger.info(executeString);
        return updateDB(executeString) > 0;
    }

    public boolean deleteRecipeFromDB(String recipeName) {
        String executeString = "DELETE FROM \"recipes_table\" WHERE \"name\" = '" + recipeName + "'";
        logger.info(executeString);
        return updateDB(executeString) > 0;
    }
    /*
    helping methods
     */

    private int setPiecesList(String foodName, int difference) {
        Food food = getFoodFromDB("SELECT * WHERE \"name\" = '" + foodName + "' FROM \"foods_table\"");
        int piecesList = food.getPiecesList() + difference;
        if (piecesList >= 0) {
            return updateDB("UPDATE \"foods_table\" SET \"piecesList\" = " + piecesList + " WHERE \"name\" = '" + foodName + "'");
        } else {
            logger.info("Pieces List is < 0 and cant get reduces more.");
            return updateDB("UPDATE \"foods_table\" SET \"piecesList\" = 0 WHERE \"name\" = '" + foodName + "'");
        }
    }

    private int setPiecesStock(String foodName, int difference) {
        Food food = getFoodFromDB("SELECT * WHERE \"name\" = '" + foodName + "' FROM \"foods_table\"");
        int piecesStock = food.getPiecesStock() + difference;
        if (piecesStock >= 0) {
            return updateDB("UPDATE \"foods_table\" SET \"piecesStock\" = " + piecesStock + " WHERE \"name\" = '" + foodName + "'");
        } else {
            logger.info("Pieces Stock is < 0 and cant get reduces more.");
            return updateDB("UPDATE \"foods_table\" SET \"piecesStock\" = 0 WHERE \"name\" = '" + foodName + "'");
        }
    }

    private Food getFoodFromDB(String sqlSelect) {
        Food food = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlSelect);
            food = buildFood(resultSet);
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            //e.printStackTrace();
            logger.warning("Failed to get Food out of Database.");
        } finally {
            return food;
        }
    }

    private Recipe getRecipeFromDB(String sqlSelect) {
        Recipe recipe = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlSelect);
            recipe = buildRecipe(resultSet);
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            //e.printStackTrace();
            logger.warning("Failed to get Recipe out of Database.");
        } finally {
            return recipe;
        }
    }

    private ArrayList<Food> getFoodListFromDB(String sqlSelect) {
        ArrayList<Food> list = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlSelect);
            while (resultSet.next()) {
                list.add(buildFood(resultSet));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            //e.printStackTrace();
            logger.warning("Failed to get Food List out of Database.");
        } finally {
            return list;
        }
    }

    private int updateDB(String sqlUpdate) {
        int success = 0;
        try {
            statement = connection.createStatement();
            success = statement.executeUpdate(sqlUpdate);

            statement.close();
        } catch (SQLException e) {
            //e.printStackTrace();
            logger.warning("Failed to uptdate Data in the Database.");
        } finally {
            return success;
        }
    }

    private Food buildFood(ResultSet resultSet) throws SQLException {
        return new Food(
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getInt(4),
                resultSet.getInt(5),
                resultSet.getInt(6),
                resultSet.getInt(7),
                resultSet.getInt(8),
                resultSet.getInt(9),
                resultSet.getInt(10));
    }

    private Recipe buildRecipe(ResultSet resultSet) throws SQLException {
        ArrayList<RecipePart> parts = new ArrayList<>();
        String recipeName = resultSet.getString(1);
        String recipeDescription = resultSet.getString(2);
        Recipe recipe = null;
        for (int i = 3; i <= 12; i += 2) {
            String foodName = resultSet.getString(i);
            if (foodName.equalsIgnoreCase("null")) {
                break;
            }
            parts.add(new RecipePart(getFood(foodName), resultSet.getInt(i + 1)));

        }
        recipe = new Recipe(recipeName, recipeDescription, parts);
        return recipe;
    }

    /*
    Logger
     */
    public void setLoggingLevel(Level level) {
        logger.setLevel(level);
    }
}
