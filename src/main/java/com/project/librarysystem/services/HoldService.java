package com.project.librarysystem.services;

import java.util.List;

import com.project.librarysystem.dtos.HoldDTO;

public interface HoldService {

    HoldDTO borrow(Long patronId, Long bookCopyId);
    HoldDTO devolution(Long id);
    List<HoldDTO> findAll();
    HoldDTO findById(Long id);
    
}
