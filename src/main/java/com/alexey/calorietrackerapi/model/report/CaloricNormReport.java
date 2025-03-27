package com.alexey.calorietrackerapi.model.report;

public record CaloricNormReport(
        Long userId,
        Double dailyCaloricNorm,
        Double consumedCalories,
        Boolean isGoalAchieved
) {
}
