package com.alanpmz.pharmacy_CRUD_spring.dto.supplier;

import jakarta.validation.constraints.NotBlank;

public record SupplierCreateDTO(@NotBlank(message = "Name is required") String name) {
}
