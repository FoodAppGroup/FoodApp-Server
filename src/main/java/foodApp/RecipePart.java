package foodApp;

public class RecipePart {
    private final Food food;
    private final int count;

    public RecipePart(Food food, int count) {
        this.food = food;
        this.count = count;
    }

    public Food getFood() {
        return food;
    }

    public int getCount() {
        return count;
    }
}
