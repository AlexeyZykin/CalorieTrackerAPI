package com.alexey.calorietrackerapi.service;

import com.alexey.calorietrackerapi.exception.UserAlreadyExistsException;
import com.alexey.calorietrackerapi.mapper.UserMapper;
import com.alexey.calorietrackerapi.model.User;
import com.alexey.calorietrackerapi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional
    public User createUser(User user) {
        if (userRepo.findUserByEmail(user.email()).isPresent()) {
            throw new UserAlreadyExistsException("Пользователь уже существует!");
        }

        User createdUser = UserMapper.toModel(
                userRepo.save(UserMapper.toEntity(user))
        );

        return createdUser;
    }


}
