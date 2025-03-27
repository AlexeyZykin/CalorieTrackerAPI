package com.alexey.calorietrackerapi.service;

import com.alexey.calorietrackerapi.dto.MealIntakeResponse;
import com.alexey.calorietrackerapi.entity.MealEntity;
import com.alexey.calorietrackerapi.entity.MealIntakeEntity;
import com.alexey.calorietrackerapi.entity.UserEntity;
import com.alexey.calorietrackerapi.exception.MealNotFoundException;
import com.alexey.calorietrackerapi.exception.UserNotFoundException;
import com.alexey.calorietrackerapi.mapper.MealIntakeMapper;
import com.alexey.calorietrackerapi.mapper.MealMapper;
import com.alexey.calorietrackerapi.mapper.UserMapper;
import com.alexey.calorietrackerapi.model.MealIntake;
import com.alexey.calorietrackerapi.model.User;
import com.alexey.calorietrackerapi.repo.MealIntakeRepo;
import com.alexey.calorietrackerapi.repo.MealRepo;
import com.alexey.calorietrackerapi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MealIntakeService {

    private final MealIntakeRepo mealIntakeRepo;
    private final UserRepo userRepo;
    private final MealRepo mealRepo;

    @Autowired
    public MealIntakeService(MealIntakeRepo mealIntakeRepo, UserRepo userRepo, MealRepo mealRepo) {
        this.mealIntakeRepo = mealIntakeRepo;
        this.userRepo = userRepo;
        this.mealRepo = mealRepo;
    }

    @Transactional
    public MealIntake addMealIntake(Long userId, List<Long> mealIds, LocalDate mealTime) {
        UserEntity userEntity = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Данного пользователя не существует"));

        List<MealEntity> mealEntityList = new ArrayList<>();
        for (var mealId : mealIds) {
            mealEntityList.add(
                    mealRepo.findById(mealId)
                            .orElseThrow(() -> new MealNotFoundException("Блюда с таким Id не существует: " + mealId))
            );
        }

        MealIntake mealIntake = new MealIntake(
                null,
                UserMapper.toModel(userEntity),
                mealEntityList.stream().map(MealMapper::toModel).toList(),
                mealTime
        );
        MealIntake createdMealIntake = MealIntakeMapper.toModel(
                mealIntakeRepo.save(MealIntakeMapper.toEntity(mealIntake))
        );
        return createdMealIntake;
    }


}
