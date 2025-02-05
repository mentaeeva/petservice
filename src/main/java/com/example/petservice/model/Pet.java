package com.example.petservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pet")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private int age;
}
