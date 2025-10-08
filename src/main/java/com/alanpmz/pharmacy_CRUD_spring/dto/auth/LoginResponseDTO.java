package com.alanpmz.pharmacy_CRUD_spring.dto.auth;

public record LoginResponseDTO(String accessToken, Long expiresIn) {
}
