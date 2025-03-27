package com.alexey.calorietrackerapi.model.report;

import java.time.LocalDate;
import java.util.Map;

public record MealHistoryReport(
        Long userId,
        Map<LocalDate, Double> totalCaloriesPerDay
) {
}
