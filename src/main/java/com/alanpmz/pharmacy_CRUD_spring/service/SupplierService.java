package com.alanpmz.pharmacy_CRUD_spring.service;


import com.alanpmz.pharmacy_CRUD_spring.dto.supplier.SupplierCreateDTO;
import com.alanpmz.pharmacy_CRUD_spring.dto.supplier.SupplierUpdateDTO;
import com.alanpmz.pharmacy_CRUD_spring.model.Supplier;
import com.alanpmz.pharmacy_CRUD_spring.repository.SupplierRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.alanpmz.pharmacy_CRUD_spring.dto.supplier.SupplierResponseDTO;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;


    public SupplierResponseDTO createSupplier(SupplierCreateDTO dto) {
        Supplier supplier = Supplier.builder()
                .name(dto.name())
                .build();

        Supplier saved = supplierRepository.save(supplier);
        return mapToResponseDTO(saved);
    }



    public SupplierResponseDTO getSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found with id: " + id));
        return mapToResponseDTO(supplier);
    }

    @Transactional(readOnly = true)
    public SupplierResponseDTO getSupplierByName(String name) {
        Supplier supplier = supplierRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found with name: " + name));
        return mapToResponseDTO(supplier);
    }

    @Transactional(readOnly = true)
    public List<SupplierResponseDTO> findSuppliersByNameContaining(String name) {
        return supplierRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<SupplierResponseDTO> findSuppliersByNameStartingWith(String prefix) {
        return supplierRepository.findByNameStartingWithIgnoreCase(prefix).stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    public List<SupplierResponseDTO> getAllSuppliers() {
        return supplierRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    public SupplierResponseDTO updateSupplier(Long id, SupplierUpdateDTO dto) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found with id: " + id));

        supplier.setName(dto.name());
        Supplier updated = supplierRepository.save(supplier);
        return mapToResponseDTO(updated);
    }


    public void deleteSupplier(Long id) {
        if (!supplierRepository.existsById(id)) {
            throw new EntityNotFoundException("Supplier not found with id: " + id);
        }
        supplierRepository.deleteById(id);
    }

    private SupplierResponseDTO mapToResponseDTO(Supplier supplier) {
        return new SupplierResponseDTO(supplier.getId(), supplier.getName());
    }
}
