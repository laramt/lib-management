package com.project.librarysystem.dtos;


import com.project.librarysystem.models.enums.BookStatus;

import lombok.Data;

@Data
public class BookCopyDTO {

    private Long id;
    private String isbn;
    private int yearPublished;
    private BookStatus status;
    private BookDTO book;
    private PublisherDTO publisher;

}
