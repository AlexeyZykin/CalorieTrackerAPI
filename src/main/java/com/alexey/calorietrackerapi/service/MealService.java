package com.alexey.calorietrackerapi.service;


import com.alexey.calorietrackerapi.entity.MealEntity;
import com.alexey.calorietrackerapi.exception.MealAlreadyExistsException;
import com.alexey.calorietrackerapi.mapper.MealMapper;
import com.alexey.calorietrackerapi.model.Meal;
import com.alexey.calorietrackerapi.repo.MealRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MealService {

    private final MealRepo mealRepo;

    @Autowired
    public MealService(MealRepo mealRepo) {
        this.mealRepo = mealRepo;
    }


    @Transactional
    public Meal createMeal(Meal meal) {
        if (mealRepo.findMealEntityByName(meal.name()).isPresent()) {
            throw new MealAlreadyExistsException("Данное блюдо уже существует!");
        }

        MealEntity mealEntity = MealMapper.toEntity(meal);
        Meal createdMeal = MealMapper.toModel(
                mealRepo.save(mealEntity)
        );
        return createdMeal;
    }



}
