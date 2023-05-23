package com.project.librarysystem.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.librarysystem.domain.models.enums.BookStatus;
import jakarta.persistence.*;


import java.util.HashSet;
import java.util.Set;

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

    public BookCopy() {
    }

    public BookCopy(Long id, Book book, String isbn, String publisher, int yearPublished, BookStatus status) {
        this.id = id;
        this.book = book;
        this.isbn = isbn;
        this.publisher = publisher;
        this.yearPublished = yearPublished;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public Set<Hold> getHolds() {
        return holds;
    }

    public void setHolds(Set<Hold> holds) {
        this.holds = holds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookCopy bookCopy)) return false;

        return getId() != null ? getId().equals(bookCopy.getId()) : bookCopy.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
