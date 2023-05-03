package com.project.librarysystem.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "HOLD_TB")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hold {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinTable(name = "HOLD_BOOK",
            joinColumns = @JoinColumn(name = "hold_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Book book;
    @OneToOne
    @JoinTable(name = "HOLD_PATRON",
            joinColumns = @JoinColumn(name = "hold_id"),
            inverseJoinColumns = @JoinColumn(name = "patron_id"))
    private Patron patron;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate checkout;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate checkIn;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dueDate;
    private BigDecimal lateFee;
    private boolean returned;

}
