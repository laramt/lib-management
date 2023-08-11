package com.project.librarysystem.services.impl;

import com.project.librarysystem.dtos.request.AuthorRequest;
import com.project.librarysystem.dtos.response.AuthorResponse;
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
    public List<AuthorResponse> findAll() {
        List<Author> authors = repository.findAll();
        return mapper.toAuthorResponseList(authors);
    }

    @Override
    public AuthorResponse findById(Long id) {
        Author author = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Author with id " + id + " not found."));
        return  mapper.toAuthorResponse(author);
    }

    @Override
    public AuthorResponse insert(AuthorRequest request) {
        if(request.getName() == null || request.getName().isEmpty()){
            throw new InvalidInputException("Author cannot be null or empty.");
        }
      
        Author author = repository.save(mapper.toAuthor(request));
        return mapper.toAuthorResponse(author);
    }

    @Override
    public AuthorResponse update(Long id, AuthorRequest request) {
          Author author = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Author with id " + id + " not found."));

        author = mapper.updateAuthorFromRequest(request, author);
        repository.save(author);
        return mapper.toAuthorResponse(author);
    }

    @Override
    public void delete(Long id) {
        repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Author with id " + id + " not found."));
        repository.deleteById(id);
    }

    @Override
    public List<AuthorResponse> findByName(String name) {
        List<Author> list = repository.findByNameContaining(name);
        return mapper.toAuthorResponseList(list);
    }

}
