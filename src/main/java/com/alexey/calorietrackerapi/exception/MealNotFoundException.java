package com.alexey.calorietrackerapi.exception;

public class MealNotFoundException extends RuntimeException {
    public MealNotFoundException(String message) {
        super(message);
    }
}
