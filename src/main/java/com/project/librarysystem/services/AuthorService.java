package com.project.librarysystem.services;

import java.util.List;

import com.project.librarysystem.dtos.AuthorDTO;

public interface AuthorService {

    AuthorDTO create(AuthorDTO dto);
    List<AuthorDTO> findAll();
    List<AuthorDTO> findByName(String name);
    AuthorDTO findById (Long id);
    
}
