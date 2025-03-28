package com.alexey.calorietrackerapi.service;

import com.alexey.calorietrackerapi.entity.MealEntity;
import com.alexey.calorietrackerapi.entity.MealIntakeEntity;
import com.alexey.calorietrackerapi.entity.UserEntity;
import com.alexey.calorietrackerapi.entity.UserGoalEntity;
import com.alexey.calorietrackerapi.exception.UserNotFoundException;
import com.alexey.calorietrackerapi.mapper.UserMapper;
import com.alexey.calorietrackerapi.model.MealIntake;
import com.alexey.calorietrackerapi.model.User;
import com.alexey.calorietrackerapi.repo.MealIntakeRepo;
import com.alexey.calorietrackerapi.repo.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CalorieReportServiceTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private MealIntakeRepo mealIntakeRepo;

    @InjectMocks
    private CalorieReportService calorieReportService;

    private final Long userId = 1L;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getDailyReport() throws UserNotFoundException {
        UserEntity user = new UserEntity(
                userId,
                "Alexey",
                "alex@gmail.com",
                25,
                65.0,
                175.0,
                UserGoalEntity.WEIGHT_MAINTENANCE);
        MealEntity mealEntity = new MealEntity(
                1L,
                "Пельмени",
                2000.0,
                23.1,
                12.4,
                12.3
        );
        List<MealIntakeEntity> mealIntakeList = List.of(
                new MealIntakeEntity(1L, user, List.of(mealEntity), LocalDate.parse("2023-03-01")),
                new MealIntakeEntity(2L, user, List.of(mealEntity), LocalDate.parse("2023-03-01"))
        );

        when(userRepo.findById(userId)).thenReturn(Optional.of(user));
        when(mealIntakeRepo.findByUserAndDate(any(UserEntity.class), eq(LocalDate.parse("2023-03-01"))))
                .thenReturn(mealIntakeList);

        var expected = 4000.0;

        var actual = calorieReportService.getDailyReport(userId, LocalDate.parse("2023-03-01")).totalSumOfCalories();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkDailyGoal() {
        UserEntity user = new UserEntity(
                userId,
                "Alexey",
                "alex@gmail.com",
                25,
                65.0,
                175.0,
                UserGoalEntity.WEIGHT_MAINTENANCE);
        MealEntity mealEntity = new MealEntity(
                1L,
                "Пельмени",
                2000.0,
                23.1,
                12.4,
                12.3
        );
        List<MealIntakeEntity> mealIntakeList = List.of(
                new MealIntakeEntity(1L, user, List.of(mealEntity), LocalDate.parse("2023-03-01")),
                new MealIntakeEntity(2L, user, List.of(mealEntity), LocalDate.parse("2023-03-01"))
        );

        when(userRepo.findById(userId)).thenReturn(Optional.of(user));
        when(mealIntakeRepo.findByUserAndDate(any(UserEntity.class), eq(LocalDate.parse("2023-03-01"))))
                .thenReturn(mealIntakeList);

        var expected = UserMapper.toModel(user).calculateDailyCalorieNorm() >= 4000.0;

        var actual = calorieReportService.checkDailyGoal(userId, LocalDate.parse("2023-03-01")).isGoalAchieved();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getMealHistoryPerDay() {
        UserEntity user = new UserEntity(
                userId,
                "Alexey",
                "alex@gmail.com",
                25,
                65.0,
                175.0,
                UserGoalEntity.WEIGHT_MAINTENANCE);
        MealEntity mealEntity = new MealEntity(
                1L,
                "Пельмени",
                2000.0,
                23.1,
                12.4,
                12.3
        );
        List<MealIntakeEntity> mealIntakeList = List.of(
                new MealIntakeEntity(1L, user, List.of(mealEntity), LocalDate.parse("2023-03-01")),
                new MealIntakeEntity(2L, user, List.of(mealEntity), LocalDate.parse("2023-03-01"))
        );

        when(userRepo.findById(userId)).thenReturn(Optional.of(user));
        when(mealIntakeRepo.findByUserAndDateBetween(
                any(UserEntity.class),
                eq(LocalDate.parse("2023-03-01")),
                eq(LocalDate.parse("2023-03-02"))))
                .thenReturn(mealIntakeList);

        var expected = 4000.0;

        var actual = calorieReportService
                .getMealHistoryPerDay(userId, LocalDate.parse("2023-03-01"), LocalDate.parse("2023-03-02"))
                .totalCaloriesPerDay()
                .get(LocalDate.parse("2023-03-01"));

        Assertions.assertEquals(expected, actual);
    }
}