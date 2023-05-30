package com.project.librarysystem.domain.services;

import com.project.librarysystem.api.dtos.EmailDTO;
import com.project.librarysystem.domain.models.Hold;

import java.util.List;

public interface EmailService {

    void sendBorrowedBook(Hold hold);
    List<EmailDTO> findAll();
    EmailDTO findById(Long id);
    
}
