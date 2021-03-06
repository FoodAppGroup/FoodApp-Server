package com.spring.model.entity;

public enum Category {
    VEGETABLES, // Translation: Gemüse
    FRUIT, // Translation: Obst
    PASTA; // Translation: Teigwaren

    public static Category getValue(String string) throws IllegalArgumentException {
        for (Category entity : Category.class.getEnumConstants()) {
            if (entity.toString().equalsIgnoreCase(string)) {
                return entity;
            }
        }
        throw new IllegalArgumentException("No match with a Category-Enum: " + string);
    }
}
