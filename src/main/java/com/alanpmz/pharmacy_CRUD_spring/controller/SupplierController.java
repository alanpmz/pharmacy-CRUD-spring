package com.alanpmz.pharmacy_CRUD_spring.controller;

import com.alanpmz.pharmacy_CRUD_spring.dto.supplier.SupplierCreateDTO;
import com.alanpmz.pharmacy_CRUD_spring.dto.supplier.SupplierResponseDTO;
import com.alanpmz.pharmacy_CRUD_spring.dto.supplier.SupplierUpdateDTO;
import com.alanpmz.pharmacy_CRUD_spring.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping
    public ResponseEntity<SupplierResponseDTO> createSupplier(@Valid @RequestBody SupplierCreateDTO dto) {
        SupplierResponseDTO created = supplierService.createSupplier(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> getSupplierById(@PathVariable Long id) {
        SupplierResponseDTO supplier = supplierService.getSupplierById(id);
        return ResponseEntity.ok(supplier);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<SupplierResponseDTO> getSupplierByName(@PathVariable String name) {
        SupplierResponseDTO supplier = supplierService.getSupplierByName(name);
        return ResponseEntity.ok(supplier);
    }

    @GetMapping("/name/contains")
    public ResponseEntity<List<SupplierResponseDTO>> searchSuppliersByName(
            @RequestParam String contains) {
        List<SupplierResponseDTO> suppliers = supplierService.findSuppliersByNameContaining(contains);
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/name/prefix")
    public ResponseEntity<List<SupplierResponseDTO>> findSuppliersByPrefix(
            @RequestParam String prefix) {
        List<SupplierResponseDTO> suppliers = supplierService.findSuppliersByNameStartingWith(prefix);
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponseDTO>> getAllSuppliers() {
        List<SupplierResponseDTO> suppliers = supplierService.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> updateSupplier(
            @PathVariable Long id,
            @Valid @RequestBody SupplierUpdateDTO dto) {
        SupplierResponseDTO updated = supplierService.updateSupplier(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }
}
