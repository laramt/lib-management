package com.project.librarysystem.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "HOLD_TB")
@Builder
public class Hold {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
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
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime checkout;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime checkIn;
    private LocalDate dueDate;
    private BigDecimal lateFee;
    private boolean returned;

}
