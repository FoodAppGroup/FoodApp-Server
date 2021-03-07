package testutils;

import com.spring.dataprovider.FoodExcelManagement;
import com.spring.logging.Console;
import com.spring.model.Food;

import java.io.IOException;
import java.util.List;

public class TestMain {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<Food> list = FoodExcelManagement.readTable();
        Console.log(list.toString());
        FoodExcelManagement.writeTable(list);

        Console.log("test");
        Console.log("test");
        Console.log("test");
        Console.log("test");
        Console.log("test123");
        Console.log("test");
        Console.log("test");
        Console.log("test");
        Console.log("test");
        Console.log("test");
        Console.log("testend");

    }
}
