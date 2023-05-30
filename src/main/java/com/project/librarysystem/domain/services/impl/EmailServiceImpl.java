package com.project.librarysystem.domain.services.impl;

import com.project.librarysystem.api.dtos.EmailDTO;
import com.project.librarysystem.domain.repositories.EmailRepository;
import com.project.librarysystem.domain.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailRepository repository;

    @Override
    public EmailDTO send(EmailDTO dto) {
        return null;
    }

    @Override
    public List<EmailDTO> findAll() {
        return null;
    }

    @Override
    public EmailDTO findById(Long id) {
        return null;
    }
}
