package com.project.librarysystem.services;

import com.project.librarysystem.dtos.PatronDTO;
import com.project.librarysystem.models.Patron;

import java.util.List;

public interface PatronService {

    PatronDTO registerNewPatron(PatronDTO patron);
    List<PatronDTO> findAll();
    PatronDTO findById(Long id);
    PatronDTO update(Long id, PatronDTO patron);

}
