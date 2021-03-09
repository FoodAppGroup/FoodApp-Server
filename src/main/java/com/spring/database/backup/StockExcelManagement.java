package com.spring.database.backup;

import com.spring.dataprovider.PropertyReader;
import com.spring.model.Stock;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

public class StockExcelManagement extends ExcelManagement<Stock> {

    @Override
    protected void initData() {
        filePath = PropertyReader.getInstance().getExcel_StockTablePath();
        sheetName = "Stock";

        header.put(0, "ProductName");
        header.put(1, "Number");
    }

    @Override
    protected void writeRow(Row row, Stock stock, CellStyle style) {
        //writeCell(row, 0, stock.get, style);
        writeCell(row, 1, stock.getNumber().toString(), style);
    }

    @Override
    protected Stock readRow(Row row) {
        Stock stock = new Stock();
        //stock.setKey(new StockKey(readString(row, 0)));
        stock.setNumber(readInt(row, 1));
        return stock;
    }
}
