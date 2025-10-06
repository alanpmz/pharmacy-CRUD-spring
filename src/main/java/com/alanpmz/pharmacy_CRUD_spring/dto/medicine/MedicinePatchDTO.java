package com.alanpmz.pharmacy_CRUD_spring.dto.medicine;

import jakarta.validation.constraints.Positive;

import java.time.OffsetDateTime;

public record MedicinePatchDTO(String name,
                               String dosageForm,
                               @Positive(message = "Dosage must be positive") Integer dosage,
                               @Positive(message = "Quantity must be positive") Integer quantity,
                               @Positive(message = "Price must be positive") Double price,
                               OffsetDateTime expiryDate,
                               Long supplierId) {
}
