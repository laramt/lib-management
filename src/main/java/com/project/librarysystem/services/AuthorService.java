package com.project.librarysystem.services;

import java.util.List;

import com.project.librarysystem.dtos.AuthorDTO;
import com.project.librarysystem.models.Author;

public interface AuthorService {

    List<AuthorDTO> findAll();
    AuthorDTO findById (Long id);
    Author getOrCreateAuthor(Author author);

}
