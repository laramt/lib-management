package com.project.librarysystem.services;

import com.project.librarysystem.models.Book;

import java.util.List;

public interface BookService {

    Book save(Book book);
    List<Book> findAll();
    Book findById (Long id);
    void delete (Long id);
    Book update (Long id, Book book);

}
