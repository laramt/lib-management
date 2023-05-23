package com.project.librarysystem.mappers;

import com.project.librarysystem.dtos.PatronDTO;
import com.project.librarysystem.models.Patron;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PatronMapper {

    private final ModelMapper mapper;

    public Patron toPatron(PatronDTO dto) {
        return mapper.map(dto, Patron.class);
    }

    public PatronDTO toPatronDTO(Patron entity) {
        return mapper.map(entity, PatronDTO.class);
    }

    public List<PatronDTO> toPatronDTOList(List<Patron> patrons) {
        return patrons.stream()
                .map(this::toPatronDTO)
                .collect(Collectors.toList());
    }
}
