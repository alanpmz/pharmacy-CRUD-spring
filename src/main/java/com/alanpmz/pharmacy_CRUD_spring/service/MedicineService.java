package com.alanpmz.pharmacy_CRUD_spring.service;

import com.alanpmz.pharmacy_CRUD_spring.dto.medicine.MedicineCreateDTO;
import com.alanpmz.pharmacy_CRUD_spring.dto.medicine.MedicinePatchDTO;
import com.alanpmz.pharmacy_CRUD_spring.dto.medicine.MedicineResponseDTO;
import com.alanpmz.pharmacy_CRUD_spring.model.Medicine;
import com.alanpmz.pharmacy_CRUD_spring.model.Supplier;
import com.alanpmz.pharmacy_CRUD_spring.repository.MedicineRepository;
import com.alanpmz.pharmacy_CRUD_spring.repository.SupplierRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MedicineService {

    private final MedicineRepository medicineRepository;
    private final SupplierRepository supplierRepository;

    public MedicineResponseDTO createMedicine(MedicineCreateDTO dto) {
        Supplier supplier = supplierRepository.findById(dto.supplierId())
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found with id: " + dto.supplierId()));

        if (medicineRepository.existsByNameAndDosageFormAndDosage(dto.name(), dto.dosageForm(), dto.dosage())) {
            throw new EntityExistsException("Medicine with same name, dosage form and dosage already exists");
        }

        Medicine medicine = Medicine.builder()
                .name(dto.name())
                .dosageForm(dto.dosageForm())
                .dosage(dto.dosage())
                .quantity(dto.quantity())
                .price(dto.price())
                .expiryDate(dto.expiryDate())
                .supplier(supplier)
                .build();

        Medicine saved = medicineRepository.save(medicine);
        return mapToResponseDTO(saved);
    }

    @Transactional(readOnly = true)
    public MedicineResponseDTO getMedicineById(Long id) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medicine not found with id: " + id));
        return mapToResponseDTO(medicine);
    }

    @Transactional(readOnly = true)
    public MedicineResponseDTO getMedicineByName(String name) {
        Medicine medicine = medicineRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Medicine not found with name: " + name));
        return mapToResponseDTO(medicine);
    }

    @Transactional(readOnly = true)
    public List<MedicineResponseDTO> findMedicinesByNameContaining(String name) {
        return medicineRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MedicineResponseDTO> findMedicinesByNameStartingWith(String prefix) {
        return medicineRepository.findByNameStartingWithIgnoreCase(prefix).stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public MedicineResponseDTO getMedicineByNameAndDosageForm(String name, String dosageForm) {
        Medicine medicine = medicineRepository.findByNameAndDosageForm(name, dosageForm)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Medicine not found with name: " + name + " and dosage form: " + dosageForm));
        return mapToResponseDTO(medicine);
    }

    @Transactional(readOnly = true)
    public List<MedicineResponseDTO> findMedicinesByNameContainingAndDosageForm(String name, String dosageForm) {
        return medicineRepository.findByNameContainingIgnoreCaseAndDosageForm(name, dosageForm).stream()
                .map(this::mapToResponseDTO)
                .toList();
    }


    @Transactional(readOnly = true)
    public List<MedicineResponseDTO> getAllMedicines() {
        return medicineRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MedicineResponseDTO> findByDosageForm(String dosageForm) {
        return medicineRepository.findByDosageForm(dosageForm).stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MedicineResponseDTO> findByDosage(Integer dosage) {
        return medicineRepository.findByDosage(dosage).stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MedicineResponseDTO> findByDosageGreaterThan(Integer dosage) {
        return medicineRepository.findByDosageGreaterThan(dosage).stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MedicineResponseDTO> findByDosageLessThan(Integer dosage) {
        return medicineRepository.findByDosageLessThan(dosage).stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MedicineResponseDTO> findByDosageFormAndDosage(String dosageForm, Integer dosage) {
        return medicineRepository.findByDosageFormAndDosage(dosageForm, dosage).stream()
                .map(this::mapToResponseDTO)
                .toList();
    }


    @Transactional(readOnly = true)
    public List<MedicineResponseDTO> findByQuantity(Integer quantity) {
        return medicineRepository.findByQuantity(quantity).stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MedicineResponseDTO> findByQuantityGreaterThan(Integer quantity) {
        return medicineRepository.findByQuantityGreaterThan(quantity).stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MedicineResponseDTO> findByQuantityLessThan(Integer quantity) {
        return medicineRepository.findByQuantityLessThan(quantity).stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MedicineResponseDTO> findByPrice(Double price) {
        return medicineRepository.findByPrice(price).stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MedicineResponseDTO> findByPriceGreaterThan(Double price) {
        return medicineRepository.findByPriceGreaterThan(price).stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MedicineResponseDTO> findByPriceLessThan(Double price) {
        return medicineRepository.findByPriceLessThan(price).stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MedicineResponseDTO> findByExpiryDate(OffsetDateTime expiryDate) {
        return medicineRepository.findByExpiryDate(expiryDate).stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MedicineResponseDTO> findByExpiryDateAfter(OffsetDateTime expiryDate) {
        return medicineRepository.findByExpiryDateAfter(expiryDate).stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MedicineResponseDTO> findByExpiryDateBefore(OffsetDateTime expiryDate) {
        return medicineRepository.findByExpiryDateBefore(expiryDate).stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MedicineResponseDTO> getExpiredMedicines() {
        return medicineRepository.findByExpiryDateBefore(OffsetDateTime.now()).stream()
                .map(this::mapToResponseDTO)
                .toList();
    }



    public MedicineResponseDTO patchMedicine(Long id, MedicinePatchDTO dto) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medicine not found with id: " + id));

        if (dto.name() != null) {
            if (dto.name().isBlank()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Medicine name cannot be blank");
            }
            medicine.setName(dto.name());
        }

        if (dto.dosageForm() != null) {
            medicine.setDosageForm(dto.dosageForm());
        }

        if (dto.dosage() != null) {
            medicine.setDosage(dto.dosage());
        }

        if (dto.quantity() != null) {
            medicine.setQuantity(dto.quantity());
        }

        if (dto.price() != null) {
            medicine.setPrice(dto.price());
        }

        if (dto.expiryDate() != null) {
            medicine.setExpiryDate(dto.expiryDate());
        }

        if (dto.supplierId() != null) {
            Supplier supplier = supplierRepository.findById(dto.supplierId())
                    .orElseThrow(() -> new EntityNotFoundException("Supplier not found with id: " + dto.supplierId()));
            medicine.setSupplier(supplier);
        }

        return mapToResponseDTO(medicine);
    }

    public void deleteMedicine(Long id) {
        if (!medicineRepository.existsById(id)) {
            throw new EntityNotFoundException("Medicine not found with id: " + id);
        }
        medicineRepository.deleteById(id);
    }

    public MedicineResponseDTO updateStock(Long id, Integer newQuantity) {
        if (newQuantity < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantity name cannot be negative");
        }

        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medicine not found with id: " + id));

        medicine.setQuantity(newQuantity);
        return mapToResponseDTO(medicine);
    }

    @Transactional(readOnly = true)
    public List<MedicineResponseDTO> getMedicinesBySupplier(Long supplierId) {
        if (!supplierRepository.existsById(supplierId)) {
            throw new EntityNotFoundException("Supplier not found with id: " + supplierId);
        }

        return medicineRepository.findBySupplierId(supplierId).stream()
                .map(this::mapToResponseDTO)
                .toList();
    }


    private MedicineResponseDTO mapToResponseDTO(Medicine medicine) {
        return new MedicineResponseDTO(
                medicine.getId(),
                medicine.getName(),
                medicine.getDosageForm(),
                medicine.getDosage(),
                medicine.getQuantity(),
                medicine.getPrice(),
                medicine.getExpiryDate(),
                medicine.getSupplier().getId()
        );
    }
}
