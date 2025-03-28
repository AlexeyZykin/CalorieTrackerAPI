package com.alexey.calorietrackerapi.mapper;

import com.alexey.calorietrackerapi.dto.MealIntakeResponse;
import com.alexey.calorietrackerapi.entity.MealIntakeEntity;
import com.alexey.calorietrackerapi.model.MealIntake;

import java.util.Collections;
import java.util.List;

public class MealIntakeMapper {

    public static MealIntake toModel(MealIntakeEntity entity) {
        return new MealIntake(
                entity.getMealIntakeId(),
                UserMapper.toModel(entity.getUser()),
                entity.getMeals().stream().map(MealMapper::toModel).toList(),
                entity.getDate()
        );
    }

    public static MealIntakeEntity toEntity(MealIntake model) {
        return new MealIntakeEntity(
                model.mealIntakeId(),
                UserMapper.toEntity(model.user()),
                model.meals().stream().map(MealMapper::toEntity).toList(),
                model.date()
        );
    }

    public static List<MealIntake> toModelList(List<MealIntakeEntity> entities) {
        if (entities == null) return Collections.emptyList();
        return entities.stream().map(MealIntakeMapper::toModel).toList();
    }

    public static MealIntakeResponse toResponse(MealIntake model) {
        return new MealIntakeResponse(
                model.mealIntakeId(),
                UserMapper.toDto(model.user()),
                model.meals().stream().map(MealMapper::toDto).toList(),
                model.date()
        );
    }
}