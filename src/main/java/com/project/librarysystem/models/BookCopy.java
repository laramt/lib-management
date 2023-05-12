package com.project.librarysystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.librarysystem.enums.BookStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
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

    public BookCopy(Book book, String isbn, String publisher, int yearPublished, BookStatus status) {
        this.book = book;
        this.isbn = isbn;
        this.publisher = publisher;
        this.yearPublished = yearPublished;
        this.status = status;
    }

}
