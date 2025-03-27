package com.alexey.calorietrackerapi.dto;

import java.time.LocalDate;
import java.util.List;

public record MealIntakeResponse(
        Long mealIntakeId,
        UserDto user,
        List<MealDto> meals,
        LocalDate date
) {
}