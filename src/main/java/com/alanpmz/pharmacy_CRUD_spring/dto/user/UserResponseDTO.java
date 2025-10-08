package com.alanpmz.pharmacy_CRUD_spring.dto.user;

import com.alanpmz.pharmacy_CRUD_spring.model.Role;

import java.util.Set;

public record UserResponseDTO(String id, String username, Set<Role> roles) {
}
