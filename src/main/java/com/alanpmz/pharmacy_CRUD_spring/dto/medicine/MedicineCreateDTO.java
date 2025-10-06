package com.alanpmz.pharmacy_CRUD_spring.dto.medicine;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.OffsetDateTime;

public record MedicineCreateDTO(@NotBlank(message = "Name is required") String name,
                          @NotBlank(message = "Dosage form is required") String dosageForm,
                          @Positive(message = "Dosage must be positive") Integer dosage,
                          @Positive(message = "Quantity must be positive") Integer quantity,
                          @Positive(message = "Price must be positive") Double price,
                          @NotNull(message = "Date is required")
                                @Future(message = "Date must be in the future") OffsetDateTime expiryDate,
                          @NotNull(message = "Supplier ID is required") Long supplierId) {
}
