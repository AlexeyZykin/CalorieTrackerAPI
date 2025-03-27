package com.alexey.calorietrackerapi.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.List;

public record MealIntakeRequest(

        @NotNull(message = "Id пользователя не может быть null")
        Long userId,

        @NotEmpty(message = "Список блюд не может быть пустым")
        List<Long> mealIds,

        @PastOrPresent(message = "Дата приема пищи не может быть в будущем")
        LocalDate date
) {
}
