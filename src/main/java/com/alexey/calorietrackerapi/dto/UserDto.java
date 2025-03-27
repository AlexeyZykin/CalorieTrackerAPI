package com.alexey.calorietrackerapi.dto;


import com.alexey.calorietrackerapi.validation.OnCreate;
import com.alexey.calorietrackerapi.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record UserDto(
        @NotNull(groups = OnUpdate.class)
        @Null(groups = OnCreate.class)
        @JsonProperty("user_id")
        Long userId,

        @NotBlank(message = "Имя не может быть пустым")
        @JsonProperty("name")
        String name,

        @NotBlank(message = "Email не может быть пустым")
        @Email(message = "Некорректный формат email")
        @JsonProperty("email")
        String email,

        @Min(value = 1, message = "Возраст должен быть не менее 1 года")
        @Max(value = 120, message = "Возраст должен быть не более 120 лет")
        @JsonProperty("age")
        int age,

        @Min(value = 15, message = "Вес должен быть не менее 15 кг")
        @Max(value = 300, message = "Вес должен быть не более 300 кг")
        @JsonProperty("weight")
        double weight,

        @Min(value = 100, message = "Рост должен быть не менее 100 см")
        @Max(value = 250, message = "Рост должен быть не более 250 см")
        @JsonProperty("height")
        double height,

        @NotNull
        @JsonProperty("user_goal")
        UserGoalDto userGoalDto
) {
}
