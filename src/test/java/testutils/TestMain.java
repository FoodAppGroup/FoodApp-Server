package testutils;

import com.spring.dataprovider.databaseBackup.ProductExcelManagement;
import com.spring.logging.Console;
import com.spring.model.Product;

import java.io.IOException;
import java.util.List;

public class TestMain {
    public static void main(String[] args) throws IOException, InterruptedException {
        ProductExcelManagement pem = ProductExcelManagement.getInstance();
        List<Product> list = pem.readTable();
        Console.log(list.toString());
        pem.writeTable(list);

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
