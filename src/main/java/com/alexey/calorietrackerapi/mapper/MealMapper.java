package com.alexey.calorietrackerapi.mapper;

import com.alexey.calorietrackerapi.dto.MealDto;
import com.alexey.calorietrackerapi.entity.MealEntity;
import com.alexey.calorietrackerapi.model.Meal;

import java.util.List;

public class MealMapper {

    public static MealDto toDto(Meal meal) {
        return new MealDto(
                meal.mealId(),
                meal.name(),
                meal.caloriesPerServing(),
                meal.proteins(),
                meal.fats(),
                meal.carbohydrates()
        );
    }

    public static Meal toModel(MealDto dto) {
        return new Meal(
                dto.mealId(),
                dto.name(),
                dto.caloriesPerServing(),
                dto.proteins(),
                dto.fats(),
                dto.carbohydrates()
        );
    }

    public static MealEntity toEntity(Meal meal) {
        MealEntity entity = new MealEntity();
        entity.setMealId(meal.mealId());
        entity.setName(meal.name());
        entity.setCaloriesPerServing(meal.caloriesPerServing());
        entity.setProteins(meal.proteins());
        entity.setFats(meal.fats());
        entity.setCarbohydrates(meal.carbohydrates());
        return entity;
    }

    public static Meal toModel(MealEntity entity) {
        return new Meal(
                entity.getMealId(),
                entity.getName(),
                entity.getCaloriesPerServing(),
                entity.getProteins(),
                entity.getFats(),
                entity.getCarbohydrates()
        );
    }

    public static List<MealDto> toDtoList(List<Meal> meals) {
        return meals.stream().map(MealMapper::toDto).toList();
    }

    public static List<Meal> toModelList(List<MealEntity> entities) {
        return entities.stream().map(MealMapper::toModel).toList();
    }
}
