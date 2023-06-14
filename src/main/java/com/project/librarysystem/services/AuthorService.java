package com.project.librarysystem.services;

import java.util.List;

import com.project.librarysystem.dtos.AuthorDTO;

public interface AuthorService {

    AuthorDTO insert(AuthorDTO dto);
    List<AuthorDTO> findAll();
    AuthorDTO findById (Long id);
    
}
