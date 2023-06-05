package com.project.librarysystem.mappers;

import com.project.librarysystem.dtos.AuthorDTO;
import com.project.librarysystem.models.Author;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthorMapper {
    private final ModelMapper mapper;

    public Author toAuthor(AuthorDTO dto) {
        return mapper.map(dto, Author.class);
    }

    public AuthorDTO toAuthorDTO(Author entity) {
        return mapper.map(entity, AuthorDTO.class);
    }

    public List<AuthorDTO> toAuthorDTOList(List<Author> authors) {
        return authors.stream()
                .map(this::toAuthorDTO)
                .collect(Collectors.toList());
    }

}
