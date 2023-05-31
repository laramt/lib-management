package com.project.librarysystem.services;

import com.project.librarysystem.dtos.EmailDTO;
import com.project.librarysystem.models.Hold;

import java.util.List;

public interface EmailService {

    void sendBorrowedBook(Hold hold);
    void sendLateBook(Hold hold);
    List<EmailDTO> findAll();
    EmailDTO findById(Long id);
    
}
