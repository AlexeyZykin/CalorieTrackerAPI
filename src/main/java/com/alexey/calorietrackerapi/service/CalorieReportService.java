package com.alexey.calorietrackerapi.service;


import com.alexey.calorietrackerapi.exception.UserNotFoundException;
import com.alexey.calorietrackerapi.mapper.MealIntakeMapper;
import com.alexey.calorietrackerapi.mapper.UserMapper;
import com.alexey.calorietrackerapi.model.Meal;
import com.alexey.calorietrackerapi.model.MealIntake;
import com.alexey.calorietrackerapi.model.User;
import com.alexey.calorietrackerapi.model.report.CaloricNormReport;
import com.alexey.calorietrackerapi.model.report.DailyCalorieReport;
import com.alexey.calorietrackerapi.model.report.MealHistoryReport;
import com.alexey.calorietrackerapi.repo.MealIntakeRepo;
import com.alexey.calorietrackerapi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CalorieReportService {

    private final UserRepo userRepo;
    private final MealIntakeRepo mealIntakeRepo;

    @Autowired
    public CalorieReportService(UserRepo userRepo, MealIntakeRepo mealIntakeRepo) {
        this.userRepo = userRepo;
        this.mealIntakeRepo = mealIntakeRepo;
    }

    @Transactional(readOnly = true)
    public DailyCalorieReport getDailyReport(Long userId, LocalDate date) throws UserNotFoundException {
        User user = UserMapper.toModel(
                userRepo.findById(userId)
                        .orElseThrow(() -> new UserNotFoundException("Данного пользователя не существует!"))
        );

        List<MealIntake> mealIntakeList =
                mealIntakeRepo.findByUserAndDate(UserMapper.toEntity(user), date)
                        .stream()
                        .map(MealIntakeMapper::toModel)
                        .toList();

        Double totalSumOfCalories = calculateTotalSumOfCalories(mealIntakeList);
        int mealIntakeCount = mealIntakeList.size();

        return new DailyCalorieReport(
                user.userId(),
                totalSumOfCalories,
                mealIntakeCount,
                date
        );
    }

    private Double calculateTotalSumOfCalories(List<MealIntake> mealIntakeList) {
        return mealIntakeList.stream()
                .flatMap(intake -> intake.meals().stream())
                .mapToDouble(Meal::caloriesPerServing)
                .sum();
    }

    @Transactional(readOnly = true)
    public CaloricNormReport checkDailyGoal(Long userId, LocalDate date) throws UserNotFoundException {
        User user = UserMapper.toModel(
                userRepo.findById(userId)
                        .orElseThrow(() -> new UserNotFoundException("Данного пользователя не существует!"))
        );

        Double consumedCalories = mealIntakeRepo.findByUserAndDate(UserMapper.toEntity(user), date)
                .stream()
                .map(MealIntakeMapper::toModel).mapToDouble(intake ->
                        intake.meals().stream()
                                .mapToDouble(Meal::caloriesPerServing)
                                .sum()).sum();

        Double dailyCaloricNorm = user.calculateDailyCalorieNorm();
        Boolean isGoalAchieved = dailyCaloricNorm >= consumedCalories;
        return new CaloricNormReport(
                user.userId(),
                dailyCaloricNorm,
                consumedCalories,
                isGoalAchieved
        );
    }

    @Transactional(readOnly = true)
    public MealHistoryReport getMealHistoryPerDay(Long userId, LocalDate startDate, LocalDate endDate) throws UserNotFoundException {
        User user = UserMapper.toModel(
                userRepo.findById(userId)
                        .orElseThrow(() -> new UserNotFoundException("Данного пользователя не существует!"))
        );

        List<MealIntake> mealIntakeList =
                mealIntakeRepo
                        .findByUserAndDateBetween(UserMapper.toEntity(user), startDate, endDate)
                        .stream()
                        .map(MealIntakeMapper::toModel)
                        .sorted(Comparator.comparing(MealIntake::date))
                        .toList();

        Map<LocalDate, Double> totalCaloriesPerDay = mealIntakeList.stream()
                .collect(Collectors.groupingBy(MealIntake::date,
                        Collectors.summingDouble(intake ->
                                intake.meals().stream()
                                        .mapToDouble(Meal::caloriesPerServing)
                                        .sum()
                        )
                ));

        return new MealHistoryReport(user.userId(), totalCaloriesPerDay);
    }

}
