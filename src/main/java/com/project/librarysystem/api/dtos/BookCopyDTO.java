package com.project.librarysystem.api.dtos;

import com.project.librarysystem.domain.models.enums.BookStatus;
import com.project.librarysystem.domain.models.Book;
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
