package com.project.librarysystem.dtos;


import com.project.librarysystem.models.Hold;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class PatronDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String cardNumber;
    private String name;
    private String email;
    private String phoneNumber;
    private LocalDate birthDate;
   // private List<Hold> holds = new ArrayList<>();

}
