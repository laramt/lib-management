package com.project.librarysystem.dtos.response;


import com.project.librarysystem.models.enums.BookStatus;

import lombok.Data;

@Data
public class BookCopyResponse {

    private Long id;
    private String isbn;
    private int yearPublished;
    private BookStatus status;
    private BookResponse book;
    private PublisherResponse publisher;

}
