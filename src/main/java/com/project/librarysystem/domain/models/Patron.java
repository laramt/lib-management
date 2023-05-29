package com.project.librarysystem.domain.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PATRON_TB")
@Data
public class Patron {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardNumber;
    private String name;
    private String email;
    private String phoneNumber;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;
    @JsonIgnore
    @OneToMany(mappedBy = "patron")
    private List<Hold> holds = new ArrayList<>();

}