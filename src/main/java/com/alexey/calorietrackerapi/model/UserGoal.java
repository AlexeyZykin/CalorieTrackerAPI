package com.alexey.calorietrackerapi.model;

public enum UserGoal {
    WEIGHT_LOSS,
    WEIGHT_MAINTENANCE,
    WEIGHT_GAIN;

    public double getGoalCoefficient() {
        return switch (this) {
            case WEIGHT_LOSS -> 0.85;
            case WEIGHT_MAINTENANCE -> 1.0;
            case WEIGHT_GAIN -> 1.2;
        };
    }
}
