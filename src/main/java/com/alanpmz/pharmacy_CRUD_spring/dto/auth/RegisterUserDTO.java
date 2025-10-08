package com.alanpmz.pharmacy_CRUD_spring.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record RegisterUserDTO(@NotBlank(message = "Username is required.") String username,
                              @NotBlank(message = "Password is required.") String password) {
}
