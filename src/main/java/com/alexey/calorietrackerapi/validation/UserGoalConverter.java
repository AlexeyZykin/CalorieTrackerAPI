package com.alexey.calorietrackerapi.validation;

import com.alexey.calorietrackerapi.dto.UserGoalDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserGoalConverter implements Converter<String, UserGoalDto> {


    @Override
    public UserGoalDto convert(String source) {
        try {
            return UserGoalDto.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Недопустимое значение user goal");
        }
    }
}
