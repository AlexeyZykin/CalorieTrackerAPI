package com.alexey.calorietrackerapi.model.report;

import java.time.LocalDate;

public record DailyCalorieReport(
        Long userId,
        double totalSumOfCalories,
        int mealIntakeCount,
        LocalDate dateOfReport
) {
}
