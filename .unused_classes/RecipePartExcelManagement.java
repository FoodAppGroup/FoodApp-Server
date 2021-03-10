package com.spring.database.backup;

import com.spring.dataprovider.PropertyReader;
import com.spring.model.entity.RecipePart;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

public class RecipePartExcelManagement extends ExcelManagement<RecipePart> {

    @Override
    protected void initData() {
        filePath = PropertyReader.getInstance().getExcel_RecipePartTablePath();
        sheetName = "RecipeParts";

        header.put(0, "ProductName");
        header.put(1, "RecipeName");
        header.put(2, "Number");
    }

    @Override
    protected void writeRow(Row row, RecipePart recipePart, CellStyle style) {
//        writeCell(row, 0, recipePart.getKey().getRecipeName(), style);
//        writeCell(row, 1, recipePart.getKey().getProductName(), style);
        writeCell(row, 2, recipePart.getNumber().toString(), style);
    }

    @Override
    protected RecipePart readRow(Row row) {
        RecipePart recipePart = new RecipePart();
//        recipePart.setKey(new RecipePartKey(
//                readString(row, 0),
//                readString(row, 1)
//        ));
        recipePart.setNumber(readInt(row, 2));
        return recipePart;
    }
}
