package com.example.vet_mvc_app.authentication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "email is required")
    @Email
    @Size(min = 3, max = 50, message = "email must be between 3 - 50")
    private String email;

    @NotBlank(message = "password is required")
    @Size(min = 6, max = 50, message = "password must be between 6 - 50")
    private String password;
}
