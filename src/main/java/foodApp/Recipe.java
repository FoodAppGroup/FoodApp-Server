package foodApp;

import java.util.ArrayList;

public class Recipe {

    private final String name;
    private final ArrayList<RecipePart> parts;
    private final String description;
    private final int kcal;
    private final int carbonhydrates;
    private final int protein;
    private final int fat;


    public Recipe(String name, String description, ArrayList<RecipePart> parts) {
        this.name = name;
        this.parts = parts;
        this.description = description;

        kcal = calcField(1);
        carbonhydrates = calcField(2);
        protein = calcField(3);
        fat = calcField(4);
    }

    private int calcField(int index) {
        int result = 0;
        for (RecipePart part : parts) {
            switch (index) {
                case 1:
                    result += part.getFood().getKcal() * part.getCount();
                    break;
                case 2:
                    result += part.getFood().getCarbohydrates() * part.getCount();
                    break;
                case 3:
                    result += part.getFood().getProtein() * part.getCount();
                    break;
                case 4:
                    result += part.getFood().getFat() * part.getCount();
                    break;
                default:
                    System.out.println("Something went wrong by calcField in Recipe.");
            }
        }
        return result;
    }


    public String getName() {
        return name;
    }

    public ArrayList<RecipePart> getParts() {
        return parts;
    }

    public String getDescription() {
        return description;
    }

    public int getKcal() {
        return kcal;
    }

    public int getCarbonhydrates() {
        return carbonhydrates;
    }

    public int getProtein() {
        return protein;
    }

    public int getFat() {
        return fat;
    }

    public boolean equals(Recipe recipe) {
        return this.name.equalsIgnoreCase(recipe.getName());
    }
}
