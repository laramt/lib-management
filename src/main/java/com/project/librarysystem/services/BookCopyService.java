package com.project.librarysystem.services;

import java.util.List;

import com.project.librarysystem.dtos.request.BookCopyRequest;
import com.project.librarysystem.dtos.response.BookCopyResponse;

public interface BookCopyService {

    BookCopyResponse newBookCopy(BookCopyRequest request);
    List<BookCopyResponse> findAll();
    BookCopyResponse findById (Long id);
    void delete (Long id);
    BookCopyResponse update (Long id, BookCopyRequest request);

}
