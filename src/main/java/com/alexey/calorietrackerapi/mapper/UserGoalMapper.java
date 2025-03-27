package com.alexey.calorietrackerapi.mapper;

import com.alexey.calorietrackerapi.dto.UserGoalDto;
import com.alexey.calorietrackerapi.entity.UserGoalEntity;
import com.alexey.calorietrackerapi.model.UserGoal;

public class UserGoalMapper {

    public static UserGoal fromDtoToModel(UserGoalDto userGoalDto) {
        if (userGoalDto == null) return null;
        return switch (userGoalDto) {
            case WEIGHT_GAIN -> UserGoal.WEIGHT_GAIN;
            case WEIGHT_LOSS -> UserGoal.WEIGHT_LOSS;
            case WEIGHT_MAINTENANCE -> UserGoal.WEIGHT_MAINTENANCE;
        };
    }

    public static UserGoalDto fromModelToDto(UserGoal userGoal) {
        if (userGoal == null) return null;
        return switch (userGoal) {
            case WEIGHT_GAIN -> UserGoalDto.WEIGHT_GAIN;
            case WEIGHT_LOSS -> UserGoalDto.WEIGHT_LOSS;
            case WEIGHT_MAINTENANCE -> UserGoalDto.WEIGHT_MAINTENANCE;
        };
    }

    public static UserGoal fromEntityToModel(UserGoalEntity userGoalEntity) {
        if (userGoalEntity == null) return null;
        return switch (userGoalEntity) {
            case WEIGHT_GAIN -> UserGoal.WEIGHT_GAIN;
            case WEIGHT_LOSS -> UserGoal.WEIGHT_LOSS;
            case WEIGHT_MAINTENANCE -> UserGoal.WEIGHT_MAINTENANCE;
        };
    }

    public static UserGoalEntity fromModelToEntity(UserGoal userGoal) {
        if (userGoal == null) return null;
        return switch (userGoal) {
            case WEIGHT_GAIN -> UserGoalEntity.WEIGHT_GAIN;
            case WEIGHT_LOSS -> UserGoalEntity.WEIGHT_LOSS;
            case WEIGHT_MAINTENANCE -> UserGoalEntity.WEIGHT_MAINTENANCE;
        };
    }
}
