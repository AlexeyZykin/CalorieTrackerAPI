package com.alexey.calorietrackerapi.controller;

import com.alexey.calorietrackerapi.dto.MealIntakeRequest;
import com.alexey.calorietrackerapi.dto.MealIntakeResponse;
import com.alexey.calorietrackerapi.exception.MealNotFoundException;
import com.alexey.calorietrackerapi.exception.UserNotFoundException;
import com.alexey.calorietrackerapi.mapper.MealIntakeMapper;
import com.alexey.calorietrackerapi.service.MealIntakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/calorie-tracker/mealintake")
public class MealIntakeController {

    private final MealIntakeService mealIntakeService;

    @Autowired
    public MealIntakeController(MealIntakeService mealIntakeService) {
        this.mealIntakeService = mealIntakeService;
    }

    @PostMapping
    public ResponseEntity<String> addMealIntake(MealIntakeRequest mealIntakeRequest) {
        try {
            MealIntakeResponse createdMealIntake = MealIntakeMapper.toResponse(
                    mealIntakeService.addMealIntake(mealIntakeRequest.userId(), mealIntakeRequest.mealIds(), mealIntakeRequest.date())
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(createdMealIntake.toString());
        } catch (UserNotFoundException | MealNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
