package com.project.librarysystem.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Entity
@Table(name = "BOOK_TB")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(columnDefinition = "TEXT")
    private String description;
    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private List<BookCopy> copies = new ArrayList<>();

}