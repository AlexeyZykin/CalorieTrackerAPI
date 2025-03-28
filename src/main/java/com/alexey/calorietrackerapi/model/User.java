package com.alexey.calorietrackerapi.model;


import java.util.List;


public record User(
        Long userId,
        String name,
        String email,
        int age,
        double weight,
        double height,
        UserGoal userGoal

) {
    public Double calculateDailyCalorieNorm() {
        double BMR = (10 * weight + 6.25 * height - 5 * age);
        return BMR * userGoal.getGoalCoefficient();
    }
}
