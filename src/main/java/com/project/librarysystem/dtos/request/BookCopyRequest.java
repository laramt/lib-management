package com.project.librarysystem.dtos.request;


import com.project.librarysystem.models.enums.BookStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookCopyRequest {

    private String isbn;
    private int yearPublished;
    private BookStatus status;
    private Long bookId;
    private Long publisherId;

}
