package com.spring.database.backup;

import com.spring.dataprovider.PropertyReader;
import com.spring.model.entity.Shopping;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

public class ShoppingExcelManagement extends ExcelManagement<Shopping> {
    /**
     * This method should init following fields:
     * <ul>
     *     <li>{@link ExcelManagement#filePath}</li>
     *     <li>{@link ExcelManagement#sheetName}</li>
     *     <li>{@link ExcelManagement#header}</li>
     * </ul>
     */
    @Override
    protected void initData() {
        filePath = PropertyReader.getInstance().getExcel_ShoppingListTablePath();
        sheetName = "Shopping";

        header.put(0, "List Name");
        header.put(1, "Product Name");
        header.put(2, "Number");
    }

    /**
     * This method should map the data into the cells of the row. Use {@link ExcelManagement#writeCell(Row, int, String, CellStyle)} to insert data.
     *
     * @param row    given row
     * @param object given object
     * @param style  given style
     */
    @Override
    protected void writeRow(Row row, Shopping object, CellStyle style) {
        writeCell(row, 0, object.getKey().getListName(), style);
        writeCell(row, 1, object.getKey().getProductName(), style);
        writeCell(row, 2, object.getNumber().toString(), style);
    }

    /**
     * This method should extract to data from the cells and build an object. Following methods can be used for correct cell input:
     * <ul>
     *     <li>{@link ExcelManagement#readInt(Row, int)}</li>
     *     <li>{@link ExcelManagement#readString(Row, int)}</li>
     * </ul>
     *
     * @param row given row
     * @return object of the row
     */
    @Override
    protected Shopping readRow(Row row) {
        Shopping shopping = new Shopping();
        shopping.setKey(new Shopping.Key(
                readString(row, 0),
                readString(row, 1)));
        shopping.setNumber(shopping.getNumber());
        return shopping;
    }
}
