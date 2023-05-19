package com.project.librarysystem.services;

import com.project.librarysystem.models.Hold;

import java.util.List;

public interface HoldService {

    Hold checkout(Long patronId, Long bookCopyId);
    Hold devolution(Long id);
    List<Hold> findAll();
    Hold findById(Long id);
    
}
