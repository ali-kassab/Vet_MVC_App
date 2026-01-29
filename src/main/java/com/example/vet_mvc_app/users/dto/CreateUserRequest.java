package com.example.vet_mvc_app.users.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUserRequest {

    @NotBlank
    @Size(min = 3, max = 50, message = "name must be between 3 - 50")
    private String name;
    @NotBlank
    @Size(min = 3, max = 50, message = "email must be between 3 - 50")
    private String email;
    @NotBlank
    @Size(min = 6, max = 50, message = "password must be between 3 - 50")
    private String password;

}
