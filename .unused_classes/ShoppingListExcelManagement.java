package com.spring.database.backup;

import com.spring.dataprovider.PropertyReader;
import com.spring.model.entity.ShoppingList;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

public class ShoppingListExcelManagement extends ExcelManagement<ShoppingList> {

    @Override
    protected void initData() {
        filePath = PropertyReader.getInstance().getExcel_ShoppingListTablePath();
        sheetName = "ShoppingList";

        header.put(0, "ListName");
        header.put(1, "ProductName");
        header.put(2, "Number");
    }

    @Override
    protected void writeRow(Row row, ShoppingList shoppingList, CellStyle style) {
//        writeCell(row, 0, shoppingList.getKey().getListName(), style);
//        writeCell(row, 1, shoppingList.getKey().getProductName(), style);
        writeCell(row, 2, shoppingList.getNumber().toString(), style);
    }

    @Override
    protected ShoppingList readRow(Row row) {
        ShoppingList shoppingList = new ShoppingList();
//        shoppingList.setKey(new ShoppingListKey(
//                readString(row, 0),
//                readString(row, 1)));
        shoppingList.setNumber(readInt(row, 2));
        return shoppingList;
    }
}
