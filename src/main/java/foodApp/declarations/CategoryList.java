package foodApp.declarations;

public enum CategoryList {

    OBSTundGEMUESE, TEIGWAREN, BACKEN, FERTIGPRODUKTE, TIEFGEFROHREN, GEKUEHLT, SNACKS, SONSTIGES;

    @SuppressWarnings("MethodComplexity")
    public static CategoryList formEnumList(String categoryString) {
        if (categoryString.equalsIgnoreCase("Obst") || categoryString.equalsIgnoreCase("Gemüse")) {
            return CategoryList.OBSTundGEMUESE;
        } else if (categoryString.equalsIgnoreCase("Teigwaren")) {
            return CategoryList.TEIGWAREN;
        } else if (categoryString.equalsIgnoreCase("Backen")) {
            return CategoryList.BACKEN;
        } else if (categoryString.equalsIgnoreCase("Fertigprodukt")) {
            return CategoryList.FERTIGPRODUKTE;
        } else if (categoryString.equalsIgnoreCase("Tiefgefrohren")) {
            return CategoryList.TIEFGEFROHREN;
        } else if (categoryString.equalsIgnoreCase("Gekühlt")) {
            return CategoryList.GEKUEHLT;
        } else if (categoryString.equalsIgnoreCase("Sacks")) {
            return CategoryList.SNACKS;
        } else {
            return CategoryList.SONSTIGES;
        }
    }
}
