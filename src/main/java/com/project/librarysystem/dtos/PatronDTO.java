package com.project.librarysystem.dtos;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class PatronDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String cardNumber;
    private String name;
    private String email;
    private String phoneNumber;
    private LocalDate birthDate;

}
