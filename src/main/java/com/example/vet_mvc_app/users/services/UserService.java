package com.example.vet_mvc_app.users.services;

import com.example.vet_mvc_app.users.dto.CreateUserRequest;
import com.example.vet_mvc_app.users.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    String createUser(CreateUserRequest request);
}
