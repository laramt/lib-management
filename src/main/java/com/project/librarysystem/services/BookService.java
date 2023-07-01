package com.project.librarysystem.services;

import java.util.List;

import com.project.librarysystem.dtos.request.BookRequest;
import com.project.librarysystem.dtos.response.BookResponse;
import com.project.librarysystem.models.Book;

public interface BookService {

    List<BookResponse> findAll();
    BookResponse findById (Long id);
    BookResponse insert(BookRequest request);
    Book findByTitleAndAuthor(String title, String authorName);

}
