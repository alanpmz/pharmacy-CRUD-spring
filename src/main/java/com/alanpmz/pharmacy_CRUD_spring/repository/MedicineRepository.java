package com.alanpmz.pharmacy_CRUD_spring.repository;

import com.alanpmz.pharmacy_CRUD_spring.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    Optional<Medicine> findByName(String name);

    List<Medicine> findByNameContainingIgnoreCase(String name);

    List<Medicine> findByNameStartingWithIgnoreCase(String name);

    Optional<Medicine> findByNameAndDosageForm(String name, String dosageForm);

    List<Medicine> findByNameContainingIgnoreCaseAndDosageForm(String name, String dosageForm);

    boolean existsByNameAndDosageFormAndDosage(String name, String dosageForm, int dosage);

    List<Medicine> findByDosageForm(String dosageForm);
    List<Medicine> findByDosage(Integer dosage);
    List<Medicine> findByDosageGreaterThan(Integer dosage);
    List<Medicine> findByDosageLessThan(Integer dosage);
    List<Medicine> findByDosageFormAndDosage(String dosageForm, Integer dosage);

    List<Medicine> findByQuantity(Integer quantity);
    List<Medicine> findByQuantityGreaterThan(Integer quantity);
    List<Medicine> findByQuantityLessThan(Integer quantity);

    List<Medicine> findByPrice(Double price);
    List<Medicine> findByPriceGreaterThan(Double price);
    List<Medicine> findByPriceLessThan(Double price);

    List<Medicine> findByExpiryDate(OffsetDateTime expiryDate);
    List<Medicine> findByExpiryDateAfter(OffsetDateTime expiryDate);
    List<Medicine> findByExpiryDateBefore(OffsetDateTime expiryDate);

    List<Medicine> findBySupplierId(Long supplierId);
}
