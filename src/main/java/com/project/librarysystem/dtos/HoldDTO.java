package com.project.librarysystem.dtos;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.project.librarysystem.models.BookCopy;
import com.project.librarysystem.models.Patron;

@Data
public class HoldDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private BookCopy bookCopy;
    private Patron patron;
    private LocalDate borrowedDate;
    private LocalDate checkInDate;
    private LocalDate dueDate;
    private BigDecimal lateFee;
    private boolean returned;

}
