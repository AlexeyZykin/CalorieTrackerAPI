package com.alexey.calorietrackerapi.controller;


import com.alexey.calorietrackerapi.dto.MealDto;
import com.alexey.calorietrackerapi.exception.MealAlreadyExistsException;
import com.alexey.calorietrackerapi.mapper.MealMapper;
import com.alexey.calorietrackerapi.model.Meal;
import com.alexey.calorietrackerapi.service.MealService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/calorietracker/meal")
@Validated
public class MealController {

    private final MealService mealService;

    @Autowired
    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @PostMapping
    public ResponseEntity<String> createMeal(@Valid @RequestBody MealDto mealDto) {
        try {
            Meal createdMeal = mealService.createMeal(MealMapper.toModel(mealDto));
            MealDto responseMeal = MealMapper.toDto(createdMeal);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseMeal.toString());
        } catch (MealAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
