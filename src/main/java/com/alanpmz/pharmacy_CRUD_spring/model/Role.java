package com.alanpmz.pharmacy_CRUD_spring.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    @AllArgsConstructor
    @Getter
    public enum Values {

        ADMIN(1L),
        USER(2L);

        private final Long id;
    }
}
