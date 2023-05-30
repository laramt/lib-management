package com.project.librarysystem.domain.services;

import com.project.librarysystem.api.dtos.EmailDTO;

import java.util.List;

public interface EmailService {

    EmailDTO send(EmailDTO dto);
    List<EmailDTO> findAll();
    EmailDTO findById(Long id);
    
}
