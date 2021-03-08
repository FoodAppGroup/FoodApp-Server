package com.spring.dataprovider.databaseBackup;

import com.spring.dataprovider.PropertyReader;
import com.spring.model.entity.ShoppingListEntity;
import com.spring.model.entity.compositeKey.ShoppingListKey;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

public class ShoppingListExcelManagement extends ExcelManagement<ShoppingListEntity> {

    @Override
    protected void initData() {
        filePath = PropertyReader.getInstance().getExcel_ShoppingListTablePath();
        sheetName = "ShoppingList";

        header.put(0, "ListName");
        header.put(1, "ProductName");
        header.put(2, "Number");
    }

    @Override
    protected void writeRow(Row row, ShoppingListEntity shoppingList, CellStyle style) {
        writeCell(row, 0, shoppingList.getKey().getListName(), style);
        writeCell(row, 1, shoppingList.getKey().getProductName(), style);
        writeCell(row, 2, shoppingList.getNumber().toString(), style);
    }

    @Override
    protected ShoppingListEntity readRow(Row row) {
        ShoppingListEntity shoppingList = new ShoppingListEntity();
        shoppingList.setKey(new ShoppingListKey(
                readString(row, 0),
                readString(row, 1)));
        shoppingList.setNumber(readInt(row, 2));
        return shoppingList;
    }
}
