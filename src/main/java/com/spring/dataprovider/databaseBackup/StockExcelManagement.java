package com.spring.dataprovider.databaseBackup;

import com.spring.dataprovider.PropertyReader;
import com.spring.model.entity.StockEntity;
import com.spring.model.entity.compositeKey.StockKey;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

public class StockExcelManagement extends ExcelManagement<StockEntity> {

    @Override
    protected void initData() {
        filePath = PropertyReader.getInstance().getExcel_StockTablePath();
        sheetName = "Stock";

        header.put(0, "ProductName");
        header.put(1, "Number");
    }

    @Override
    protected void writeRow(Row row, StockEntity stock, CellStyle style) {
        writeCell(row, 0, stock.getKey().getProductName(), style);
        writeCell(row, 1, stock.getNumber().toString(), style);
    }

    @Override
    protected StockEntity readRow(Row row) {
        StockEntity stock = new StockEntity();
        stock.setKey(new StockKey(readString(row, 0)));
        stock.setNumber(readInt(row, 1));
        return stock;
    }
}
