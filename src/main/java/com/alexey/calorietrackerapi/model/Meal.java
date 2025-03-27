package com.alexey.calorietrackerapi.model;

import com.alexey.calorietrackerapi.validation.OnCreate;
import com.alexey.calorietrackerapi.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record Meal(
        Long mealId,

        String name,

        double caloriesPerServing,

        double proteins,

        double fats,

        double carbohydrates
) {
}
