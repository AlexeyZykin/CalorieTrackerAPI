package com.alexey.calorietrackerapi.service;

import com.alexey.calorietrackerapi.model.User;
import com.alexey.calorietrackerapi.model.UserGoal;
import com.alexey.calorietrackerapi.repo.UserRepo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Collections;

@SpringBootTest
@Testcontainers
class UserServiceTest {

    @Container
    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserService userService;

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @AfterEach
    void tearDown() {
        jdbcTemplate.execute("TRUNCATE TABLE calorie_tracker_schema.user CASCADE;");
    }

    @BeforeAll
    static void startContainer() {
        container.start();
    }

    @AfterAll
    static void stopContainer() {
        container.stop();
    }


    @Test
    void createUser() {
        User user = new User(
                null,
                "Alexey",
                "alex@gmail.com",
                25,
                65.0,
                175.0,
                UserGoal.WEIGHT_MAINTENANCE,
                null
        );

        var expected = user.email();
        var actual = userService.createUser(user).email();

        Assertions.assertEquals(expected, actual);
    }
}