package com.project.librarysystem.domain.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "EMAIL_TB")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String emailFrom;
    private String emailTo;
    @ManyToOne
    @JoinColumn(name = "patron_id")
    private Patron patron;
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String text;
    private LocalDateTime sendDateEmail;

    @PrePersist
    protected void onCreate() {
        sendDateEmail = LocalDateTime.now();
    }

}
