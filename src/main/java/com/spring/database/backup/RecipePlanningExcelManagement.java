package com.spring.database.backup;

import com.spring.dataprovider.PropertyReader;
import com.spring.model.entity.RecipePlanningEntity;
import com.spring.model.entity.compositeKey.RecipePlanningKey;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

public class RecipePlanningExcelManagement extends ExcelManagement<RecipePlanningEntity> {

    @Override
    protected void initData() {
        filePath = PropertyReader.getInstance().getExcel_RecipePlanningTablePath();
        sheetName = "RecipePlanning";

        header.put(0, "RecipeName");
    }

    @Override
    protected void writeRow(Row row, RecipePlanningEntity recipePlanning, CellStyle style) {
        writeCell(row, 0, recipePlanning.getKey().getRecipeName(), style);
    }

    @Override
    protected RecipePlanningEntity readRow(Row row) {
        RecipePlanningEntity recipePlanning = new RecipePlanningEntity();
        recipePlanning.setKey(new RecipePlanningKey(
                readString(row, 0)));
        return recipePlanning;
    }
}
