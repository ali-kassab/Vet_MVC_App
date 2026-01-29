package com.example.vet_mvc_app.users.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
public class UserResponse {
    private Long id;
    @Setter
    private String name;
    @Setter
    private String email;
    @Setter
    private String role;
    private Instant created_at;
}
