package com.alanpmz.pharmacy_CRUD_spring.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "medicines")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "dosage_form")
    private String dosageForm;

    private int dosage;

    private int quantity;

    private double price;

    @Column(name = "expiry_date")
    private OffsetDateTime expiryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "supplier_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    foreignKeyDefinition = "FOREIGN KEY (supplier_id) REFERENCES suppliers(id) ON DELETE CASCADE"
            )
    )
    private Supplier supplier;
}
