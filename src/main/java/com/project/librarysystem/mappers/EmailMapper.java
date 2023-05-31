package com.project.librarysystem.mappers;

import com.project.librarysystem.dtos.EmailDTO;
import com.project.librarysystem.models.Email;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmailMapper {
    private final ModelMapper mapper;

    public Email toEmail(EmailDTO dto) {
        return mapper.map(dto, Email.class);
    }

    public EmailDTO toEmailDTO(Email entity) {
        return mapper.map(entity, EmailDTO.class);
    }

    public List<EmailDTO> toBookDTOList(List<Email> emails) {
        return emails.stream()
                .map(this::toEmailDTO)
                .collect(Collectors.toList());
    }

}