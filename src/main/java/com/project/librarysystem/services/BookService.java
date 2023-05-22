package com.project.librarysystem.services;

import com.project.librarysystem.dtos.BookDTO;
import com.project.librarysystem.models.Book;

import java.util.List;

public interface BookService {

    List<BookDTO> findAll();
    BookDTO findById (Long id);

}
