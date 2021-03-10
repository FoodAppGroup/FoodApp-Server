package com.spring.database.backup;

import com.spring.dataprovider.PropertyReader;
import com.spring.model.entity.RecipePlanning;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

public class RecipePlanningExcelManagement extends ExcelManagement<RecipePlanning> {

    @Override
    protected void initData() {
        filePath = PropertyReader.getInstance().getExcel_RecipePlanningTablePath();
        sheetName = "RecipePlanning";

        header.put(0, "RecipeName");
    }

    @Override
    protected void writeRow(Row row, RecipePlanning recipePlanning, CellStyle style) {
//        writeCell(row, 0, recipePlanning.getKey().getRecipeName(), style);
    }

    @Override
    protected RecipePlanning readRow(Row row) {
        RecipePlanning recipePlanning = new RecipePlanning();
//        recipePlanning.setKey(new RecipePlanningKey(
//                readString(row, 0)));
        return recipePlanning;
    }
}
