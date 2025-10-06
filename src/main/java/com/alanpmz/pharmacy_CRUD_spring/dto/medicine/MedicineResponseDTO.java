package com.alanpmz.pharmacy_CRUD_spring.dto.medicine;

import java.time.OffsetDateTime;

public record MedicineResponseDTO(Long id,
                                  String name,
                                  String dosageForm,
                                  int dosage,
                                  int quantity,
                                  double price,
                                  OffsetDateTime expiryDate,
                                  Long supplierId) {
}