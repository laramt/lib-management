package com.project.librarysystem.services;

import java.util.List;

import com.project.librarysystem.dtos.BookCopyDTO;

public interface BookCopyService {

    BookCopyDTO newBookCopy(BookCopyDTO bookCopy);
    List<BookCopyDTO> findAll();
    BookCopyDTO findById (Long id);
    void delete (Long id);
    BookCopyDTO update (Long id, BookCopyDTO bookCopy);

}
