package com.project.librarysystem.models;

import com.project.librarysystem.enums.BookStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "BOOK_TB")
public class Book {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private UUID id;
    private String title;
    private String author;
    @Column(unique = true)
    private String isbn;
    private String publisher;
    private int yearPublished;
    private BookStatus status;

}
