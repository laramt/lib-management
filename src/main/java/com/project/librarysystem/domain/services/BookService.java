package com.project.librarysystem.domain.services;

import com.project.librarysystem.api.dtos.BookDTO;

import java.util.List;

public interface BookService {

    List<BookDTO> findAll();
    BookDTO findById (Long id);

}
