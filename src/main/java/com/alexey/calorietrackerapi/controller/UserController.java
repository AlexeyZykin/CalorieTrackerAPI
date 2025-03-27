package com.alexey.calorietrackerapi.controller;

import com.alexey.calorietrackerapi.dto.UserDto;
import com.alexey.calorietrackerapi.exception.UserAlreadyExistsException;
import com.alexey.calorietrackerapi.mapper.UserMapper;
import com.alexey.calorietrackerapi.model.User;
import com.alexey.calorietrackerapi.service.UserService;
import com.alexey.calorietrackerapi.validation.OnCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/calorie-tracker/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@Validated(OnCreate.class) UserDto userDto) {
        try {
            User createdUser = userService.createUser(UserMapper.toModel(userDto));
            UserDto responseUser = UserMapper.toDto(createdUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseUser.toString());
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
