package testutils;

import com.spring.database.FoodExcelManagement;
import com.spring.model.Food;

import java.io.IOException;
import java.util.List;

public class TestMain {
    public static void main(String[] args) throws IOException {
        List<Food> list = new FoodExcelManagement().readTable();
        System.out.println(list.toString());
        new FoodExcelManagement().writeTable(list);

    }
}
