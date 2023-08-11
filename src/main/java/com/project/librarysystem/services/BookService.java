package com.project.librarysystem.services;

import java.util.List;

import com.project.librarysystem.dtos.request.BookRequest;
import com.project.librarysystem.dtos.response.BookResponse;

public interface BookService {

    List<BookResponse> findAll();
    BookResponse findById (Long id);
    BookResponse insert(BookRequest request);

}
