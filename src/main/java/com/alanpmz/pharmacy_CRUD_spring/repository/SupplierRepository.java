package com.alanpmz.pharmacy_CRUD_spring.repository;

import com.alanpmz.pharmacy_CRUD_spring.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    boolean existsByName(String name);
    Optional<Supplier> findByName(String name);
    List<Supplier> findByNameContainingIgnoreCase(String name);
    List<Supplier> findByNameStartingWithIgnoreCase(String name);
}
