package com.alexey.calorietrackerapi.mapper;

import com.alexey.calorietrackerapi.dto.UserDto;
import com.alexey.calorietrackerapi.entity.UserEntity;
import com.alexey.calorietrackerapi.model.User;

import java.util.Collections;

public class UserMapper {

    public static UserDto toDto(User user) {
        return new UserDto(
                user.userId(),
                user.name(),
                user.email(),
                user.age(),
                user.weight(),
                user.height(),
                UserGoalMapper.fromModelToDto(user.userGoal())
        );
    }

    public static User toModel(UserDto dto) {
        return new User(
                dto.userId(),
                dto.name(),
                dto.email(),
                dto.age(),
                dto.weight(),
                dto.height(),
                UserGoalMapper.fromDtoToModel(dto.userGoalDto())
        );
    }

    public static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setUserId(user.userId());
        entity.setName(user.name());
        entity.setEmail(user.email());
        entity.setAge(user.age());
        entity.setWeight(user.weight());
        entity.setHeight(user.height());
        entity.setUserGoalEntity(UserGoalMapper.fromModelToEntity(user.userGoal()));
        return entity;
    }

    public static User toModel(UserEntity entity) {
        return new User(
                entity.getUserId(),
                entity.getName(),
                entity.getEmail(),
                entity.getAge(),
                entity.getWeight(),
                entity.getHeight(),
                UserGoalMapper.fromEntityToModel(entity.getUserGoalEntity())
        );
    }
}
