package com.alexey.calorietrackerapi.model;

import com.alexey.calorietrackerapi.entity.MealEntity;
import com.alexey.calorietrackerapi.entity.UserEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

public record MealIntake(

        Long mealIntakeId,

        User user,

        List<Meal> meals,

        LocalDate date
) {
}
