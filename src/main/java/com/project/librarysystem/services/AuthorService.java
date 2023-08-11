package com.project.librarysystem.services;

import java.util.List;

import com.project.librarysystem.dtos.request.AuthorRequest;
import com.project.librarysystem.dtos.response.AuthorResponse;

public interface AuthorService {

    AuthorResponse insert(AuthorRequest request);
    List<AuthorResponse> findAll();
    AuthorResponse findById (Long id);
    List<AuthorResponse> findByName (String name);
    AuthorResponse update(Long id, AuthorRequest request);
    void delete(Long id);
    
}
