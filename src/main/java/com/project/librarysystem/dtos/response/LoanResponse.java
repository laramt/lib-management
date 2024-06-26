package com.project.librarysystem.dtos.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanResponse {
    
    private Long id;
    private LocalDate borrowedDate;
    private LocalDate checkInDate;
    private LocalDate dueDate;
    private BigDecimal lateFee;
    private boolean returned;
    private BookCopyResponse bookCopy;
    private UserResponse user;

}
