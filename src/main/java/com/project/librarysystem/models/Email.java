package com.project.librarysystem.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "EMAIL_TB")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String emailFrom;

    @NotBlank
    private String emailTo;

    @ManyToOne
    @JoinColumn(name = "patron_id")
    private Patron patron;

    @NotBlank
    private String subject;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String text;
    
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
}
