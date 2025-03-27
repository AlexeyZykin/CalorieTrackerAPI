package com.alexey.calorietrackerapi.dto;

import com.alexey.calorietrackerapi.validation.OnCreate;
import com.alexey.calorietrackerapi.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record MealDto(

        @NotNull(groups = OnUpdate.class)
        @Null(groups = OnCreate.class)
        @JsonProperty("meal_id")
        Long mealId,

        @NotBlank(message = "Название блюда не может быть пустым")
        @Size(min = 2, max = 150, message = "Название блюда должно содержать от 2 до 150 символов")
        @JsonProperty("name")
        String name,

        @Min(value = 0, message = "Не может быть отрицательных калорий")
        @Max(value = 5000, message = "Блюдо не может превышать 5000 ккал")
        @JsonProperty("calories_per_serving")
        double caloriesPerServing,

        @Min(value = 0, message = "количество белков не может быть отрицательным")
        @JsonProperty("proteins")
        double proteins,

        @Min(value = 0, message = "Количество жиров не может быть отрицательным")
        @JsonProperty("fats")
        double fats,

        @Min(value = 0, message = "Количество углеводов не может быть отрицательным")
        @JsonProperty("carbohydrates")
        double carbohydrates
) {
}
