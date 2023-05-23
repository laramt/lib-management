package com.project.librarysystem.domain.services;

import com.project.librarysystem.api.dtos.PatronDTO;

import java.util.List;

public interface PatronService {

    PatronDTO registerNewPatron(PatronDTO patron);
    List<PatronDTO> findAll();
    PatronDTO findById(Long id);
    PatronDTO update(Long id, PatronDTO patron);

}
