package com.project.librarysystem.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Table(name = "HOLD_TB")
public class Hold {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn( name = "book_copy_id")
    private BookCopy bookCopy;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate borrowedDate;

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate checkInDate;

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dueDate;

    private BigDecimal lateFee;
    
    private boolean returned;

    @PrePersist
    protected void onCreate() {
        borrowedDate = LocalDate.now();
    }

}
