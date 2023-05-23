package com.project.librarysystem.dtos;

import com.project.librarysystem.enums.BookStatus;
import com.project.librarysystem.models.Book;
import com.project.librarysystem.models.Hold;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class BookCopyDTO {

    private Long id;

    private Book book;
    private String isbn;
    private String publisher;
    private int yearPublished;
    private BookStatus status;

}
