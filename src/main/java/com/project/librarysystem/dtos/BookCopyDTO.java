package com.project.librarysystem.dtos;

import com.project.librarysystem.models.Book;
import com.project.librarysystem.models.enums.BookStatus;

import lombok.Data;

@Data
public class BookCopyDTO {

    private Long id;
    private Book book;
    private String isbn;
    private String publisher;
    private int yearPublished;
    private BookStatus status;

}
