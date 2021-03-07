package testutils;

import com.spring.database.FoodExcelManagement;
import com.spring.logging.Console;
import com.spring.model.Food;

import java.io.IOException;
import java.util.List;

public class TestMain {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<Food> list = new FoodExcelManagement().readTable();
        Console.log(list.toString());
        new FoodExcelManagement().writeTable(list);
        Thread.sleep(10000);

    }
}
