package com.project.librarysystem.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.librarysystem.domain.models.enums.BookStatus;
import jakarta.persistence.*;
import lombok.*;


import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "BOOK_COPY_TB")
public class BookCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @Column(nullable = false, unique = true)
    private String isbn;
    private String publisher;
    private int yearPublished;
    @Enumerated(EnumType.STRING)
    private BookStatus status;
    @JsonIgnore
    @OneToMany(mappedBy = "bookCopy")
    private Set<Hold> holds = new HashSet<>();

}
