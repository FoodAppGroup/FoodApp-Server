package com.spring.database.backup;

import com.spring.dataprovider.PropertyReader;
import com.spring.model.entity.Recipe;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

public class RecipeExcelManagement extends ExcelManagement<Recipe> {

    @Override
    protected void initData() {
        filePath = PropertyReader.getInstance().getExcel_RecipeTablePath();
        sheetName = "Recipes";

        header.put(0, "Name");
        header.put(1, "Description");
        header.put(2, "kCal");
        header.put(3, "Carbohydrates");
        header.put(4, "Protein");
        header.put(5, "Fat");
    }

    @Override
    protected void writeRow(Row row, Recipe recipe, CellStyle style) {
        writeCell(row, 0, recipe.getName(), style);
        writeCell(row, 1, recipe.getDescription(), style);
        writeCell(row, 2, recipe.getKCal().toString(), style);
        writeCell(row, 3, recipe.getCarbohydrates().toString(), style);
        writeCell(row, 4, recipe.getProtein().toString(), style);
        writeCell(row, 5, recipe.getFat().toString(), style);
    }

    @Override
    protected Recipe readRow(Row row) {
        Recipe recipe = new Recipe();
        recipe.setName(readString(row, 0));
        recipe.setDescription(readString(row, 1));
        recipe.setKCal(readInt(row, 2));
        recipe.setCarbohydrates(readInt(row, 3));
        recipe.setProtein(readInt(row, 4));
        recipe.setFat(readInt(row, 5));
        return recipe;
    }
}