package com.alexey.calorietrackerapi.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void calculateDailyCalorieNorm_WeightMaintenanceGoal() {
        int age = 25;
        double weight = 65.0;
        double height = 175.0;
        User user = new User(
                1L,
                "Alexey",
                "alexis@gmail.com",
                age,
                weight,
                height,
                UserGoal.WEIGHT_MAINTENANCE
        );

        double expected = 10 * weight + 6.25 * height - 5 * age;

        Assertions.assertEquals(expected, user.calculateDailyCalorieNorm());
    }

    @Test
    void calculateDailyCalorieNorm_WeightLossGoal() {
        int age = 25;
        double weight = 65.0;
        double height = 175.0;
        User user = new User(
                1L,
                "Alexey",
                "alexis@gmail.com",
                age,
                weight,
                height,
                UserGoal.WEIGHT_LOSS);

        double expected = (10 * weight + 6.25 * height - 5 * age) * 0.85;

        Assertions.assertEquals(expected, user.calculateDailyCalorieNorm());
    }

    @Test
    void calculateDailyCalorieNorm_WeightGainGoal() {
        int age = 25;
        double weight = 65.0;
        double height = 175.0;
        User user = new User(
                1L,
                "Alexey",
                "alexis@gmail.com",
                age,
                weight,
                height,
                UserGoal.WEIGHT_GAIN
        );

        double expected = (10 * weight + 6.25 * height - 5 * age) * 1.2;

        Assertions.assertEquals(expected, user.calculateDailyCalorieNorm());
    }
}