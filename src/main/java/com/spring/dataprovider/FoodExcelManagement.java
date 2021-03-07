package com.spring.dataprovider;

import com.spring.model.Food;
import com.spring.model.entity.Category;
import com.spring.model.entity.Unit;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FoodExcelManagement extends ExcelManagement {

    public static void writeTable(List<Food> list) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Foods");
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 4000);

        writeHeader(sheet, getHeaderStyle(workbook));

        CellStyle style = getContentStyle(workbook);
        style.setWrapText(true);
        for (int i = 0; i < list.size(); i++) {
            writeFoodRow(sheet, i + 1, list.get(i), style);
        }

        FileOutputStream outputStream = new FileOutputStream(PropertyReader.getInstance().getExcel_FoodTablePath());
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

    private static void writeFoodRow(Sheet sheet, int rowIndex, Food food, CellStyle style) {
        Row row = sheet.createRow(rowIndex);
        writeCell(row, 0, food.getName(), style);
        writeCell(row, 1, food.getCategory().toString(), style);
        writeCell(row, 2, String.valueOf(food.getPackageGramm()), style);
        writeCell(row, 3, String.valueOf(food.getKCal()), style);
        writeCell(row, 4, String.valueOf(food.getCarbohydrates()), style);
        writeCell(row, 5, String.valueOf(food.getProtein()), style);
        writeCell(row, 6, String.valueOf(food.getFat()), style);
        writeCell(row, 7, food.getUnit().toString(), style);
    }

    public static List<Food> readTable() throws IOException {
        List<Food> list = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(new File(PropertyReader.getInstance().getExcel_FoodTablePath())))) {
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                try {
                    list.add(readFood(sheet.getRow(i)));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static Food readFood(Row row) throws IllegalArgumentException {
        Food food = new Food();
        food.setName(readCell(row.getCell(0)));
        food.setCategory(readCategory(row.getCell(1)));
        food.setPackageGramm(readInteger(row.getCell(2)));
        food.setKCal(readInteger(row.getCell(3)));
        food.setCarbohydrates(readInteger(row.getCell(4)));
        food.setProtein(readInteger(row.getCell(5)));
        food.setFat(readInteger(row.getCell(6)));
        food.setUnit(readUnit(row.getCell(7)));
        return food;
    }

    private static Category readCategory(Cell cell) throws IllegalArgumentException {
        return Category.getValue(readCell(cell));
    }

    private static Unit readUnit(Cell cell) throws IllegalArgumentException {
        return Unit.getValue(readCell(cell));
    }
}
