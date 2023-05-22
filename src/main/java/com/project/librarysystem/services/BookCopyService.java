package com.project.librarysystem.services;

import com.project.librarysystem.dtos.BookCopyDTO;
import com.project.librarysystem.models.BookCopy;

import java.util.List;

public interface BookCopyService {

    BookCopyDTO newBookCopy(BookCopyDTO bookCopy);
    List<BookCopyDTO> findAll();
    BookCopyDTO findById (Long id);
    void delete (Long id);
    BookCopyDTO update (Long id, BookCopyDTO bookCopy);

}
