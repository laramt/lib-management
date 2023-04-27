package com.project.librarysystem.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

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
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate birthDate;


}
