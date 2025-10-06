package com.alanpmz.pharmacy_CRUD_spring.controller;

import com.alanpmz.pharmacy_CRUD_spring.dto.medicine.MedicineCreateDTO;
import com.alanpmz.pharmacy_CRUD_spring.dto.medicine.MedicinePatchDTO;
import com.alanpmz.pharmacy_CRUD_spring.dto.medicine.MedicineResponseDTO;
import com.alanpmz.pharmacy_CRUD_spring.service.MedicineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/medicines")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    @PostMapping
    public ResponseEntity<MedicineResponseDTO> createMedicine(@Valid @RequestBody MedicineCreateDTO dto) {
        MedicineResponseDTO created = medicineService.createMedicine(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicineResponseDTO> getMedicineById(@PathVariable Long id) {
        MedicineResponseDTO medicine = medicineService.getMedicineById(id);
        return ResponseEntity.ok(medicine);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<MedicineResponseDTO> getMedicineByName(@PathVariable String name) {
        MedicineResponseDTO medicine = medicineService.getMedicineByName(name);
        return ResponseEntity.ok(medicine);
    }

    @GetMapping("/name/contains")
    public ResponseEntity<List<MedicineResponseDTO>> searchMedicinesByName(
            @RequestParam String name) {
        List<MedicineResponseDTO> medicines = medicineService.findMedicinesByNameContaining(name);
        return ResponseEntity.ok(medicines);
    }

    @GetMapping("/name/prefix")
    public ResponseEntity<List<MedicineResponseDTO>> findMedicinesByPrefix(
            @RequestParam String prefix) {
        List<MedicineResponseDTO> medicines = medicineService.findMedicinesByNameStartingWith(prefix);
        return ResponseEntity.ok(medicines);
    }

    @GetMapping("/name/{name}/form/{dosageForm}")
    public ResponseEntity<MedicineResponseDTO> getMedicineByNameAndForm(
            @PathVariable String name,
            @PathVariable String dosageForm) {
        MedicineResponseDTO medicine = medicineService.getMedicineByNameAndDosageForm(name, dosageForm);
        return ResponseEntity.ok(medicine);
    }

    @GetMapping("/name/form")
    public ResponseEntity<List<MedicineResponseDTO>> searchMedicinesByNameAndForm(
            @RequestParam String name,
            @RequestParam String dosageForm) {
        List<MedicineResponseDTO> medicines = medicineService.findMedicinesByNameContainingAndDosageForm(name, dosageForm);
        return ResponseEntity.ok(medicines);
    }

    @GetMapping
    public ResponseEntity<List<MedicineResponseDTO>> getAllMedicines() {
        List<MedicineResponseDTO> medicines = medicineService.getAllMedicines();
        return ResponseEntity.ok(medicines);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<MedicineResponseDTO> patchMedicine(
            @PathVariable Long id,
            @Valid @RequestBody MedicinePatchDTO dto) {
        MedicineResponseDTO updated = medicineService.patchMedicine(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable Long id) {
        medicineService.deleteMedicine(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/dosage/form/{dosageForm}")
    public ResponseEntity<List<MedicineResponseDTO>> findByDosageForm(@PathVariable String dosageForm) {
        List<MedicineResponseDTO> medicines = medicineService.findByDosageForm(dosageForm);
        return ResponseEntity.ok(medicines);
    }

    @GetMapping("/dosage/{dosage}")
    public ResponseEntity<List<MedicineResponseDTO>> findByDosage(@PathVariable Integer dosage) {
        List<MedicineResponseDTO> medicines = medicineService.findByDosage(dosage);
        return ResponseEntity.ok(medicines);
    }

    @GetMapping("/dosage/greater/{dosage}")
    public ResponseEntity<List<MedicineResponseDTO>> findByDosageGreaterThan(@PathVariable Integer dosage) {
        List<MedicineResponseDTO> medicines = medicineService.findByDosageGreaterThan(dosage);
        return ResponseEntity.ok(medicines);
    }

    @GetMapping("/dosage/less/{dosage}")
    public ResponseEntity<List<MedicineResponseDTO>> findByDosageLessThan(@PathVariable Integer dosage) {
        List<MedicineResponseDTO> medicines = medicineService.findByDosageLessThan(dosage);
        return ResponseEntity.ok(medicines);
    }

    @GetMapping("/dosage/form/{dosageForm}/dosage/{dosage}")
    public ResponseEntity<List<MedicineResponseDTO>> findByDosageFormAndDosage(
            @PathVariable String dosageForm,
            @PathVariable Integer dosage) {
        List<MedicineResponseDTO> medicines = medicineService.findByDosageFormAndDosage(dosageForm, dosage);
        return ResponseEntity.ok(medicines);
    }

    @GetMapping("/quantity/{quantity}")
    public ResponseEntity<List<MedicineResponseDTO>> findByQuantity(@PathVariable Integer quantity) {
        List<MedicineResponseDTO> medicines = medicineService.findByQuantity(quantity);
        return ResponseEntity.ok(medicines);
    }

    @GetMapping("/quantity/greater/{quantity}")
    public ResponseEntity<List<MedicineResponseDTO>> findByQuantityGreaterThan(@PathVariable Integer quantity) {
        List<MedicineResponseDTO> medicines = medicineService.findByQuantityGreaterThan(quantity);
        return ResponseEntity.ok(medicines);
    }

    @GetMapping("/quantity/less/{quantity}")
    public ResponseEntity<List<MedicineResponseDTO>> findByQuantityLessThan(@PathVariable Integer quantity) {
        List<MedicineResponseDTO> medicines = medicineService.findByQuantityLessThan(quantity);
        return ResponseEntity.ok(medicines);
    }

    @GetMapping("/price/{price}")
    public ResponseEntity<List<MedicineResponseDTO>> findByPrice(@PathVariable Double price) {
        List<MedicineResponseDTO> medicines = medicineService.findByPrice(price);
        return ResponseEntity.ok(medicines);
    }

    @GetMapping("/price/greater/{price}")
    public ResponseEntity<List<MedicineResponseDTO>> findByPriceGreaterThan(@PathVariable Double price) {
        List<MedicineResponseDTO> medicines = medicineService.findByPriceGreaterThan(price);
        return ResponseEntity.ok(medicines);
    }

    @GetMapping("/price/less/{price}")
    public ResponseEntity<List<MedicineResponseDTO>> findByPriceLessThan(@PathVariable Double price) {
        List<MedicineResponseDTO> medicines = medicineService.findByPriceLessThan(price);
        return ResponseEntity.ok(medicines);
    }

    @GetMapping("/expiry")
    public ResponseEntity<List<MedicineResponseDTO>> findByExpiryDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime expiryDate) {

        List<MedicineResponseDTO> medicines = medicineService.findByExpiryDate(expiryDate);
        return ResponseEntity.ok(medicines);
    }

    @GetMapping("/expiry/after")
    public ResponseEntity<List<MedicineResponseDTO>> findByExpiryDateAfter(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime expiryDate) {

        List<MedicineResponseDTO> medicines = medicineService.findByExpiryDateAfter(expiryDate);
        return ResponseEntity.ok(medicines);
    }

    @GetMapping("/expiry/before")
    public ResponseEntity<List<MedicineResponseDTO>> findByExpiryDateBefore(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime expiryDate) {

        List<MedicineResponseDTO> medicines = medicineService.findByExpiryDateBefore(expiryDate);
        return ResponseEntity.ok(medicines);
    }

    @GetMapping("/expired")
    public ResponseEntity<List<MedicineResponseDTO>> getExpiredMedicines() {
        List<MedicineResponseDTO> medicines = medicineService.getExpiredMedicines();
        return ResponseEntity.ok(medicines);
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<MedicineResponseDTO> updateStock(
            @PathVariable Long id,
            @RequestParam Integer quantity) {
        MedicineResponseDTO updated = medicineService.updateStock(id, quantity);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<MedicineResponseDTO>> getMedicinesBySupplier(@PathVariable Long supplierId) {
        List<MedicineResponseDTO> medicines = medicineService.getMedicinesBySupplier(supplierId);
        return ResponseEntity.ok(medicines);
    }
}
