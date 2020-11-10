package foodApp.declarations;

public enum CategoryStock {

    SCHRANK, KUEHLSCHRANK, GEFRIERTRUHE, GEWUERZE, OBST, GEMUESE, BACKEN, SONSTIGES;

    @SuppressWarnings("MethodComplexity")
    public static CategoryStock fromEnumStock(String categoryString) {
        if (categoryString.equalsIgnoreCase("Schrank")) {
            return CategoryStock.SCHRANK;
        } else if (categoryString.equalsIgnoreCase("Kühschrank")) {
            return CategoryStock.KUEHLSCHRANK;
        } else if (categoryString.equalsIgnoreCase("Gefriertruhe")) {
            return CategoryStock.GEFRIERTRUHE;
        } else if (categoryString.equalsIgnoreCase("Obst")) {
            return CategoryStock.OBST;
        } else if (categoryString.equalsIgnoreCase("Gemüse")) {
            return CategoryStock.GEMUESE;
        } else if (categoryString.equalsIgnoreCase("Backen")) {
            return CategoryStock.BACKEN;
        } else if (categoryString.equalsIgnoreCase("Gewürze")) {
            return CategoryStock.GEWUERZE;
        } else {
            return CategoryStock.SONSTIGES;
        }

    }
}
