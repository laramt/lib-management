package com.project.librarysystem.models;

import com.project.librarysystem.enums.BookStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "BOOK_TB")
public class Book {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false, unique = true)
    private String isbn;
    private String publisher;
    private int yearPublished;
    private BookStatus status;

}
