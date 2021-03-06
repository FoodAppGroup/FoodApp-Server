package com.spring.database;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.List;

abstract class ExcelManagement<T> {

    public abstract void writeTable(List<T> list) throws IOException;

    public abstract List<T> readTable() throws IOException;

    protected CellStyle getHeaderStyle(XSSFWorkbook workbook) {
        CellStyle headerStyle = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 14);
        font.setBold(true);
        headerStyle.setFont(font);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return headerStyle;
    }

    protected CellStyle getContentStyle(XSSFWorkbook workbook) {
        return workbook.createCellStyle();
    }

    protected void writeCell(Row row, int cellIndex, String value, CellStyle style) {
        Cell cell = row.createCell(cellIndex);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }

    protected int readInteger(Cell cell) throws IllegalArgumentException {
        return (int) Double.parseDouble(readCell(cell));
    }

    protected String readCell(Cell cell) throws IllegalArgumentException {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getRichStringCellValue().getString();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                throw new IllegalArgumentException("No match with CellType: " + cell.getCellType());
        }
    }
}
