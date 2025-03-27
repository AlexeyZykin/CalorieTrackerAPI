package com.alexey.calorietrackerapi.repo;

import com.alexey.calorietrackerapi.entity.MealEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MealRepo extends JpaRepository<MealEntity, Long> {

    Optional<MealEntity> findMealEntityByName(String name);
}
