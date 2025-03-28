package com.alexey.calorietrackerapi.service;

import com.alexey.calorietrackerapi.model.Meal;
import com.alexey.calorietrackerapi.model.MealIntake;
import com.alexey.calorietrackerapi.model.User;
import com.alexey.calorietrackerapi.model.UserGoal;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
class MealIntakeServiceTest {

    @Container
    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MealService mealService;

    @Autowired
    private UserService userService;

    @Autowired
    private MealIntakeService mealIntakeService;


    @Test
    void addMealIntake() {
        User user = new User(
                null,
                "Alexey",
                "alex@gmail.com",
                25,
                65.0,
                175.0,
                UserGoal.WEIGHT_MAINTENANCE
        );
        Meal meal = new Meal(
                null,
                "Пельмени",
                2000,
                17.6,
                12.7,
                23.2
        );
        var addedUser = userService.createUser(user);
        var addedMeal = mealService.createMeal(meal);

        var actual = mealIntakeService.addMealIntake(addedUser.userId(), List.of(addedMeal.mealId()), LocalDate.EPOCH).mealIntakeId();

        assertNotNull(actual);
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @AfterEach
    void tearDown() {
        jdbcTemplate.execute("""
                TRUNCATE TABLE calorie_tracker_schema.user CASCADE;
                TRUNCATE TABLE calorie_tracker_schema.meal CASCADE;
                TRUNCATE TABLE calorie_tracker_schema.meal_intake CASCADE;
                TRUNCATE TABLE calorie_tracker_schema.meal_intake_meals CASCADE;
                """);
    }

    @BeforeAll
    static void startContainer() {
        container.start();
    }

    @AfterAll
    static void stopContainer() {
        container.stop();
    }
}