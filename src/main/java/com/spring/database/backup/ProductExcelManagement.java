package com.spring.database.backup;

import com.spring.dataprovider.PropertyReader;
import com.spring.model.entity.Product;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

public class ProductExcelManagement extends ExcelManagement<Product> {

    public ProductExcelManagement() {
        super();
    }

    @Override
    protected void initData() {
        filePath = PropertyReader.getInstance().getExcel_ProductTablePath();
        sheetName = "Products";

        header.put(0, "Name");
        header.put(1, "Category");
        header.put(2, "Package Gram");
        header.put(3, "kCal");
        header.put(4, "Carbohydrates");
        header.put(5, "Protein");
        header.put(6, "Fat");
        header.put(7, "Unit");
    }

    @Override
    protected void writeRow(Row row, Product product, CellStyle style) {
        writeCell(row, 0, product.getName(), style);
        writeCell(row, 1, product.getCategory().toString(), style);
        writeCell(row, 2, product.getPackageGram().toString(), style);
        writeCell(row, 3, product.getKCal().toString(), style);
        writeCell(row, 4, product.getCarbohydrates().toString(), style);
        writeCell(row, 5, product.getProtein().toString(), style);
        writeCell(row, 6, product.getFat().toString(), style);
        writeCell(row, 7, product.getUnit().toString(), style);
    }

    @Override
    protected Product readRow(Row row) throws IllegalArgumentException {
        Product product = new Product();
        product.setName(readString(row, 0));
        product.setCategory(readCategory(readString(row, 1)));
        product.setPackageGram(readInt(row, 2));
        product.setKCal(readInt(row, 3));
        product.setCarbohydrates(readInt(row, 4));
        product.setProtein(readInt(row, 5));
        product.setFat(readInt(row, 6));
        product.setUnit(readUnit(readString(row, 7)));
        return product;
    }

    private Product.Category readCategory(String string) throws IllegalArgumentException {
        return Product.Category.getValue(string);
    }

    private Product.Unit readUnit(String string) throws IllegalArgumentException {
        return Product.Unit.getValue(string);
    }
}
