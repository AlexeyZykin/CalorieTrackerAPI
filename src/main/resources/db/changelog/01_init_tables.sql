CREATE SCHEMA IF NOT EXISTS calorie_tracker_schema;

CREATE TABLE IF NOT EXISTS calorie_tracker_schema.user
(
    user_id   BIGSERIAL PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    email     VARCHAR(255) NOT NULL UNIQUE,
    age       INTEGER NOT NULL,
    weight    DOUBLE PRECISION NOT NULL,
    height    DOUBLE PRECISION NOT NULL,
    user_goal VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS calorie_tracker_schema.meal
(
    meal_id              BIGSERIAL PRIMARY KEY,
    name                 VARCHAR(255) NOT NULL,
    calories_per_serving DOUBLE PRECISION NOT NULL,
    proteins             DOUBLE PRECISION NOT NULL,
    fats                 DOUBLE PRECISION NOT NULL,
    carbohydrates        DOUBLE PRECISION NOT NULL
);

CREATE TABLE IF NOT EXISTS calorie_tracker_schema.meal_intake
(
    meal_intake_id BIGSERIAL PRIMARY KEY,
    user_id        BIGINT,
    date           DATE NOT NULL DEFAULT CURRENT_DATE,
    FOREIGN KEY (user_id) REFERENCES calorie_tracker_schema.user (user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS calorie_tracker_schema.meal_intake_meals
(
    meal_intake_id BIGINT NOT NULL,
    meal_id        BIGINT NOT NULL,
    PRIMARY KEY (meal_intake_id, meal_id),
    FOREIGN KEY (meal_intake_id) REFERENCES calorie_tracker_schema.meal_intake (meal_intake_id),
    FOREIGN KEY (meal_id) REFERENCES calorie_tracker_schema.meal (meal_id)
);