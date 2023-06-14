package com.project.librarysystem.dtos;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;


@Data
public class HoldDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private LocalDate borrowedDate;
    private LocalDate checkInDate;
    private LocalDate dueDate;
    private BigDecimal lateFee;
    private boolean returned;
    private BookCopyDTO bookCopy;
    private PatronDTO patron;

}
