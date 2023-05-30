package com.project.librarysystem.domain.services.impl;

import com.project.librarysystem.api.dtos.EmailDTO;
import com.project.librarysystem.domain.mappers.EmailMapper;
import com.project.librarysystem.domain.models.Email;
import com.project.librarysystem.domain.repositories.EmailRepository;
import com.project.librarysystem.domain.services.EmailService;
import com.project.librarysystem.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailRepository repository;

    private final EmailMapper mapper;

    @Override
    public EmailDTO send(EmailDTO dto) {
        return null;
    }

    @Override
    public List<EmailDTO> findAll() {
        List<Email> emails = repository.findAll();
        return mapper.toBookDTOList(emails);
    }

    @Override
    public EmailDTO findById(Long id) {
       Email email = repository.findById(id).orElseThrow(
               () -> new ResourceNotFoundException("Email with id " + id + " not found."));
       
       return mapper.toEmailDTO(email);
    }
}
