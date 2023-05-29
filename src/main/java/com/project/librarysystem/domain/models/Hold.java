package com.project.librarysystem.domain.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Builder
@Table(name = "HOLD_TB")
@Data
public class Hold {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn( name = "book_copy_id")
    private BookCopy bookCopy;
    @ManyToOne
    @JoinColumn(name = "patron_id")
    private Patron patron;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate checkout;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate checkIn;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dueDate;
    private BigDecimal lateFee;
    private boolean returned;

    @PrePersist
    protected void onCreate() {
        checkout = LocalDate.now();
    }

}
