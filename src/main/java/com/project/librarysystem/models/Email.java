package com.project.librarysystem.models;

import jakarta.persistence.*;
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
    @Column(nullable = false)
    private String emailFrom;
    @Column(nullable = false)
    private String emailTo;
    @ManyToOne
    @JoinColumn(name = "patron_id")
    private Patron patron;
    @Column(nullable = false)
    private String subject;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String text;
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
}
