package com.project.librarysystem.dtos;

import com.project.librarysystem.enums.BookStatus;
import com.project.librarysystem.models.Book;
import lombok.Data;

@Data
public class BookCopyDTO {

    private Long id;

    private Book book;
    private String isbn;
    private String publisher;
    private int yearPublished;
    private BookStatus status;
    // private Set<Hold> holds = new HashSet<>();

}
