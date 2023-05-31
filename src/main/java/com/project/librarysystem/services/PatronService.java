package com.project.librarysystem.services;

import java.util.List;

import com.project.librarysystem.dtos.PatronDTO;

public interface PatronService {

    PatronDTO registerNewPatron(PatronDTO patron);
    List<PatronDTO> findAll();
    PatronDTO findById(Long id);
    PatronDTO update(Long id, PatronDTO patron);

}
