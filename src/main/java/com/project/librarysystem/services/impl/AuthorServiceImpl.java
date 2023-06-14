package com.project.librarysystem.services.impl;

import com.project.librarysystem.dtos.AuthorDTO;
import com.project.librarysystem.exceptions.InvalidInputException;
import com.project.librarysystem.exceptions.ResourceNotFoundException;
import com.project.librarysystem.mappers.AuthorMapper;
import com.project.librarysystem.models.Author;
import com.project.librarysystem.repositories.AuthorRepository;
import com.project.librarysystem.services.AuthorService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;
    private final AuthorMapper mapper;

    @Override
    public List<AuthorDTO> findAll() {
        List<Author> authors = repository.findAll();
        return mapper.toAuthorDTOList(authors);
    }

    @Override
    public AuthorDTO findById(Long id) {
        Author author = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Author with id " + id + " not found."));
        return  mapper.toAuthorDTO(author);
    }

    @Override
    public AuthorDTO insert(AuthorDTO dto) {
        if(dto.getName() == null || dto.getName().isEmpty()){
            throw new InvalidInputException("Author cannot be null or empty.");
        }
      
        repository.save(mapper.toAuthor(dto));
        return dto;
    }


}
