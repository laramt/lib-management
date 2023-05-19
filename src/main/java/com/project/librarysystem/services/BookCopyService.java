package com.project.librarysystem.services;

import com.project.librarysystem.models.BookCopy;

import java.util.List;

public interface BookCopyService {

    BookCopy newBookCopy(BookCopy bookCopy);
    List<BookCopy> findAll();
    BookCopy findById (Long id);
    void delete (Long id);
    BookCopy update (Long id, BookCopy bookCopy);

}
