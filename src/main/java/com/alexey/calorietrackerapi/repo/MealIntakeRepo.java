package com.alexey.calorietrackerapi.repo;

import com.alexey.calorietrackerapi.entity.MealIntakeEntity;
import com.alexey.calorietrackerapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealIntakeRepo extends JpaRepository<MealIntakeEntity, Long> {

    List<MealIntakeEntity> findByUserAndDate(UserEntity user, LocalDate date);

    List<MealIntakeEntity> findByUserAndDateBetween(UserEntity user, LocalDate startDate, LocalDate endDate);
}
