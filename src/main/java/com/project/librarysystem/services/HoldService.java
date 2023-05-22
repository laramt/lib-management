package com.project.librarysystem.services;

import com.project.librarysystem.dtos.HoldDTO;

import java.util.List;

public interface HoldService {

    HoldDTO checkout(Long patronId, Long bookCopyId);
    HoldDTO devolution(Long id);
    List<HoldDTO> findAll();
    HoldDTO findById(Long id);
    
}
