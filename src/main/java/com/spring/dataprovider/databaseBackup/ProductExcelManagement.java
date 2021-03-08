package com.spring.dataprovider.databaseBackup;

import com.spring.dataprovider.PropertyReader;
import com.spring.model.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ProductExcelManagement extends ExcelManagement<Product> {

    private ProductExcelManagement(String filePath, String sheetName, Map<Integer, String> header, Map<Integer, Function<Product, ?>> getterColumn) {
        super(filePath, sheetName, header, getterColumn);
    }

    public static ProductExcelManagement getInstance() {
        String filePath = PropertyReader.getInstance().getExcel_ProductTablePath();
        String sheetName = "Products";
        Map<Integer, String> header = new HashMap<>();
        header.put(0, "Name");
        header.put(1, "Category");
        header.put(2, "PackageGram");
        header.put(3, "kCal");
        header.put(4, "Carbohydrates");
        header.put(5, "Protein");
        header.put(6, "Fat");
        header.put(7, "Unit");

        Map<Integer, Function<Product, ?>> getterColumn = new HashMap<>();
        getterColumn.put(0, Product::getName);
        getterColumn.put(1, Product::getCategory);
        getterColumn.put(2, Product::getPackageGram);
        getterColumn.put(3, Product::getKCal);
        getterColumn.put(4, Product::getCarbohydrates);
        getterColumn.put(5, Product::getProtein);
        getterColumn.put(6, Product::getFat);
        getterColumn.put(7, Product::getUnit);

        Map<Integer, Function<Product, ?>> setterColumn = new HashMap<>();
        return new ProductExcelManagement(filePath, sheetName, header, getterColumn);

    }

    @Override
    protected Product readRow(Row row) throws IllegalArgumentException {
        Product product = new Product();
        product.setName(readCell(row.getCell(0)));
        product.setCategory(readCategory(row.getCell(1)));
        product.setPackageGram(readInteger(row.getCell(2)));
        product.setKCal(readInteger(row.getCell(3)));
        product.setCarbohydrates(readInteger(row.getCell(4)));
        product.setProtein(readInteger(row.getCell(5)));
        product.setFat(readInteger(row.getCell(6)));
        product.setUnit(readUnit(row.getCell(7)));
        return product;
    }

    private Product.Category readCategory(Cell cell) throws IllegalArgumentException {
        return Product.Category.getValue(readCell(cell));
    }

    private Product.Unit readUnit(Cell cell) throws IllegalArgumentException {
        return Product.Unit.getValue(readCell(cell));
    }
}
