package com.spring.database.backup;

import com.spring.dataprovider.PropertyReader;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ExcelManagement<T> {

    protected String filePath = null;
    protected String sheetName = null;
    protected Map<Integer, String> header = new HashMap<>();

    public ExcelManagement() {
        initData();
    }

    /**
     * This method should init following fields:
     * <ul>
     *     <li>{@link ExcelManagement#filePath}</li>
     *     <li>{@link ExcelManagement#sheetName}</li>
     *     <li>{@link ExcelManagement#header}</li>
     * </ul>
     */
    protected abstract void initData();

    //=============== WRITE ============================================================================================

    public void writeTable(List<T> list) throws IOException {
        // create the sheet
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(sheetName);
        sheet.setColumnWidth(0, PropertyReader.getInstance().getExcel_ColumnWidth());
        sheet.setColumnWidth(1, PropertyReader.getInstance().getExcel_ColumnWidth());

        // create the header
        Row headerRow = sheet.createRow(0);
        CellStyle headerStyle = getHeaderStyle(workbook);
        header.forEach((cellIndex, headerValue) -> writeCell(headerRow, cellIndex, headerValue, headerStyle));

        // insert the content of the list by applying all getters
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        for (int i = 0; i < list.size(); i++) {
            writeRow(sheet.createRow(i + 1), list.get(i), style);
        }
        // write the file
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
            workbook.close();
        }
    }

    private CellStyle getHeaderStyle(XSSFWorkbook workbook) {
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

    /**
     * This method should map the data into the cells of the row. Use {@link ExcelManagement#writeCell(Row, int, String, CellStyle)} to insert data.
     *
     * @param row    given row
     * @param object given object
     * @param style  given style
     */
    protected abstract void writeRow(Row row, T object, CellStyle style);

    protected void writeCell(Row row, int cellIndex, String value, CellStyle style) {
        Cell cell = row.createCell(cellIndex);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }

    //=============== READ =============================================================================================

    public List<T> readTable() throws IOException {
        List<T> list = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(filePath))) {
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                try {
                    list.add(readRow(sheet.getRow(i)));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
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
    protected abstract T readRow(Row row);

    protected int readInt(Row row, int cellIndex) throws IllegalArgumentException {
        return (int) Double.parseDouble(readString(row, cellIndex));
    }

    protected String readString(Row row, int cellIndex) throws IllegalArgumentException {
        Cell cell = row.getCell(cellIndex);
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
