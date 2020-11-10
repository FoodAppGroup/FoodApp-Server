package foodApp;

import foodApp.declarations.CategoryList;
import foodApp.declarations.CategoryStock;

import java.util.Objects;

public class Food {
    int piecesStock;
    int piecesList;
    private String name;
    private CategoryStock stockCategory;
    private CategoryList listCategory;
    private int kcal;
    private int carbohydrates;
    private int fat;
    private int protein;
    private int grammConversionFactor;


    public Food(String name, CategoryStock stockCategory, CategoryList listCategory, int kcal, int carbohydrates, int fat, int protein, int piecesStock, int piecesList, int grammConversionFactor) {
        this.name = name;
        this.stockCategory = stockCategory;
        this.listCategory = listCategory;
        this.kcal = kcal;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
        this.protein = protein;
        this.piecesStock = piecesStock;
        this.piecesList = piecesList;
        this.grammConversionFactor = grammConversionFactor;
    }

    public Food(String name, String stockCategory, String listCategory, int kcal, int carbohydrates, int fat, int protein, int piecesStock, int piecesList, int grammConversionFactor) {
        this.name = name;
        this.stockCategory = CategoryStock.fromEnumStock(stockCategory);
        this.listCategory = CategoryList.formEnumList(listCategory);
        this.kcal = kcal;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
        this.protein = protein;
        this.piecesStock = piecesStock;
        this.piecesList = piecesList;
        this.grammConversionFactor = grammConversionFactor;
    }

    @Override
    public String toString() {
        return name + "," + stockCategory + "," + listCategory + "," + kcal + "," + carbohydrates + "," + fat + "," + protein + "," + piecesStock + "," + piecesList + "," + grammConversionFactor;
    }

    public String toStringForList() {
        return "Name: " + name + ", Anzahl: " + piecesList + this.getGrammList();
    }

    public String toStringStock() {
        return "Name: " + name + ", Anzahl: " + piecesStock + this.getGrammStock();
    }

    public String toHtmlTRStock() {
        return "<tr><td>" + name + "<td><td>" + piecesStock + "<td><td>" + this.getGrammStock() + "<td><tr>";
    }

    public String toHrmlTRList() {
        return "<tr><td>" + name + "<td><td>" + piecesList + "<td><td>" + this.getGrammStock() + "<td><tr>";
    }

    public String toStringDetails() {
        return "Name: " + name + "\n" +
                "Bestand: " + piecesStock + this.getGrammStock() + "(Kategorie: " + stockCategory.toString() + ")\n" +
                "Einkaufsliste: " + piecesList + this.getGrammList() + "(Kategorie: " + listCategory.toString() + ")\n" +
                "Kcal: " + kcal + "\n" +
                "Kohlenhydrate: " + carbohydrates + " / Proteine: " + protein + " / Fett: " + fat + "\n";

    }

    public boolean equals(Food food) {
        return this.name.equalsIgnoreCase(food.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, stockCategory, listCategory, kcal, carbohydrates, fat, protein, piecesStock, piecesList, grammConversionFactor);
    }

    /*
    Getter and Setter for all fields
    */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryStock getStockCategory() {
        return stockCategory;
    }

    public void setStockCategory(CategoryStock stockCategory) {
        this.stockCategory = stockCategory;
    }

    public CategoryList getListCategory() {
        return listCategory;
    }

    public void setListCategory(CategoryList listCategory) {
        this.listCategory = listCategory;
    }

    public int getPiecesStock() {
        return piecesStock;
    }

    public void setPiecesStock(int piecesStock) {
        this.piecesStock = piecesStock;
    }

    public int getPiecesList() {
        return piecesList;
    }

    public void setPiecesList(int piecesList) {
        this.piecesList = piecesList;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public int getGrammConversionFactor() {
        return grammConversionFactor;
    }

    public void setGrammConversionFactor(int grammConversionFactor) {
        this.grammConversionFactor = grammConversionFactor;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    private String getGrammList() {
        Integer gramm = grammConversionFactor * piecesList;
        if (!gramm.equals(0)) {
            return " (Gramm: " + gramm + " )";
        } else {
            return "";
        }
    }

    private String getGrammStock() {
        int gramm = grammConversionFactor * getPiecesStock();
        if (gramm != 0) {
            return " (Gramm: " + gramm + " )";
        } else {
            return "";
        }
    }
}
