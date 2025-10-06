package com.alanpmz.pharmacy_CRUD_spring.dto.supplier;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SupplierUpdateDTO(@NotBlank(message = "Name is required") String name) {}
