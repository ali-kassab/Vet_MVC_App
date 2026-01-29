package com.example.vet_mvc_app.users.services;

import com.example.vet_mvc_app.users.Repository.UserRepository;
import com.example.vet_mvc_app.users.dto.CreateUserRequest;
import com.example.vet_mvc_app.users.dto.UserResponse;
import com.example.vet_mvc_app.users.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplement implements UserService {
    private final UserRepository userRepository;

    public UserServiceImplement(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public String createUser(CreateUserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return "User with email " + request.getEmail() + " already exists";
        }
        User user = new User(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );
        userRepository.save(user);
        return "User created successfully";
    }
}
