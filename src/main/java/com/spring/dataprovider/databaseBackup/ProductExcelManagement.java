package com.spring.dataprovider.databaseBackup;

import com.spring.dataprovider.PropertyReader;
import com.spring.model.Product;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductExcelManagement extends ExcelManagement {

    public static void writeTable(List<Product> list) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Foods");
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 4000);

        writeHeader(sheet, getHeaderStyle(workbook));

        CellStyle style = getContentStyle(workbook);
        style.setWrapText(true);
        for (int i = 0; i < list.size(); i++) {
            writeRow(sheet, i + 1, list.get(i), style);
        }

        FileOutputStream outputStream = new FileOutputStream(PropertyReader.getInstance().getExcel_ProductTablePath());
        workbook.write(outputStream);
        workbook.close();
    }

    private static void writeHeader(Sheet sheet, CellStyle style) {
        Row row = sheet.createRow(0);
        writeCell(row, 0, "Name", style);
        writeCell(row, 1, "Category", style);
        writeCell(row, 2, "PackageGram", style);
        writeCell(row, 3, "kCal", style);
        writeCell(row, 4, "Carbohydrates", style);
        writeCell(row, 5, "Protein", style);
        writeCell(row, 6, "Fat", style);
        writeCell(row, 7, "Unit", style);
    }

    private static void writeRow(Sheet sheet, int rowIndex, Product product, CellStyle style) {
        Row row = sheet.createRow(rowIndex);
        writeCell(row, 0, product.getName(), style);
        writeCell(row, 1, product.getCategory().toString(), style);
        writeCell(row, 2, String.valueOf(product.getPackageGram()), style);
        writeCell(row, 3, String.valueOf(product.getKCal()), style);
        writeCell(row, 4, String.valueOf(product.getCarbohydrates()), style);
        writeCell(row, 5, String.valueOf(product.getProtein()), style);
        writeCell(row, 6, String.valueOf(product.getFat()), style);
        writeCell(row, 7, product.getUnit().toString(), style);
    }

    public static List<Product> readTable() throws IOException {
        List<Product> list = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(PropertyReader.getInstance().getExcel_ProductTablePath()))) {
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                try {
                    list.add(readRow(sheet.getRow(i)));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static Product readRow(Row row) throws IllegalArgumentException {
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

    private static Product.Category readCategory(Cell cell) throws IllegalArgumentException {
        return Product.Category.getValue(readCell(cell));
    }

    private static Product.Unit readUnit(Cell cell) throws IllegalArgumentException {
        return Product.Unit.getValue(readCell(cell));
    }
}
