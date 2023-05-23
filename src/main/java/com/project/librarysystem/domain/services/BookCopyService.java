package com.project.librarysystem.domain.services;

import com.project.librarysystem.api.dtos.BookCopyDTO;

import java.util.List;

public interface BookCopyService {

    BookCopyDTO newBookCopy(BookCopyDTO bookCopy);
    List<BookCopyDTO> findAll();
    BookCopyDTO findById (Long id);
    void delete (Long id);
    BookCopyDTO update (Long id, BookCopyDTO bookCopy);

}
