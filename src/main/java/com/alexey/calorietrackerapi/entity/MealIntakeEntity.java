package com.alexey.calorietrackerapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(schema = "calorie_tracker_schema", name = "meal_intake")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MealIntakeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_intake_id")
    private Long mealIntakeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToMany
    @JoinTable(
            schema = "calorie_tracker_schema",
            name = "meal_intake_meals",
            joinColumns = @JoinColumn(name = "meal_intake_id"),
            inverseJoinColumns = @JoinColumn(name = "meal_id")

    )
    private List<MealEntity> meals;

    @Column(name = "date")
    private LocalDate date;


}
