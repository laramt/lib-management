package com.project.librarysystem.domain.services;

import com.project.librarysystem.api.dtos.HoldDTO;

import java.util.List;

public interface HoldService {

    HoldDTO borrow(Long patronId, Long bookCopyId);
    HoldDTO devolution(Long id);
    List<HoldDTO> findAll();
    HoldDTO findById(Long id);
    
}
