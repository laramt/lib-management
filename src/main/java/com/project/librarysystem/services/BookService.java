package com.project.librarysystem.services;

import java.util.List;

import com.project.librarysystem.dtos.BookDTO;

public interface BookService {

    List<BookDTO> findAll();
    BookDTO findById (Long id);

}
