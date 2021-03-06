package com.spring.model.entity;

public enum Unit {
    GRAM,
    PIECES;

    public static Unit getValue(String string) throws IllegalArgumentException {
        for (Unit entity : Unit.class.getEnumConstants()) {
            if (entity.toString().equalsIgnoreCase(string)) {
                return entity;
            }
        }
        throw new IllegalArgumentException("No match with a Unit-Enum: " + string);
    }
}
