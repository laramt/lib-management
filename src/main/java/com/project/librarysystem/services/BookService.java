package com.project.librarysystem.services;

import java.util.List;

import com.project.librarysystem.dtos.BookDTO;
import com.project.librarysystem.models.Book;

public interface BookService {

    List<BookDTO> findAll();
    BookDTO findById (Long id);
    Book getOrCreateBook(Book book);

}
