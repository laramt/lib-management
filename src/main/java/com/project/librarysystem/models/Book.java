package com.project.librarysystem.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.librarysystem.enums.BookStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "BOOK_TB")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false, unique = true)
    private String isbn;
    private String publisher;
    private int yearPublished;
    @Enumerated(EnumType.STRING)
    private BookStatus status;
    @OneToMany(mappedBy = "book")
    private Set<Hold> holds = new HashSet<>();

}
