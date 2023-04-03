package com.project.librarysystem.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Table(name = "PATRON_TB")
@Entity
public class Patron {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String cardNumber;
    private String name;
    private String email;
    private String phoneNumber;
    private LocalDate birthDate;

}
