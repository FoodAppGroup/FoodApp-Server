package foodApp;

import com.fasterxml.jackson.core.JsonProcessingException;
import foodApp.objects.Food;
import foodApp.objects.Recipe;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    private static Logger logger;

    private final String URL = "jdbc:sqlite:./src/main/resources/dataBase/food.sqlite";

    private static Connection connection;
    private static Statement statement;

    public Database() {
        //Config for logger
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
        logger = Logger.getLogger(Database.class.getName());
        logger.setLevel(Level.OFF);
        //open connection
        connection = connectToDB();
    }

    /*
    DB settings
     */

    @SneakyThrows
    private Connection connectToDB() {
        logger.info("Connecting to: " + URL);
        return DriverManager.getConnection(URL);
    }

    @SneakyThrows
    public void closeConnectionToDB() {
        logger.info("Closing connection to: " + URL);
        statement.close();
        connection.close();
    }

    /*
    DB executions
     */

    private void executeUpdateDB(String sqlUpdate) throws SQLException {
        logger.info("Executing: " + sqlUpdate);
        statement = connection.createStatement();
        statement.executeUpdate(sqlUpdate);
        statement.close();
    }

    private ResultSet executeSelectDB(String sqlSelect) throws SQLException {
        logger.info("Executing: " + sqlSelect);
        statement = connection.createStatement();
        return statement.executeQuery(sqlSelect);
    }

    /*
    Get single Object
     */
    public Food getFood(String foodName) throws SQLException {
        String sqlSelect = "SELECT * FROM \"foods_table\" WHERE \"name\"='" + foodName + "'";
        return new Food(executeSelectDB(sqlSelect));
    }

    public Recipe getRecipe(String recipeName) throws SQLException, JsonProcessingException {
        String sqlSelect = "SELECT * FROM \"recipes_table\" WHERE \"name\"='" + recipeName + "'";
        return new Recipe(executeSelectDB(sqlSelect));
    }

    /*
    Logger
     */
    public void setLoggingLevel(Level level) {
        logger.setLevel(level);
    }
}
