package com.project.librarysystem.mappers;

import com.project.librarysystem.dtos.request.AuthorRequest;
import com.project.librarysystem.dtos.response.AuthorResponse;
import com.project.librarysystem.models.Author;

import lombok.RequiredArgsConstructor;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthorMapper {
    private final ModelMapper mapper;

    public Author toAuthor(AuthorRequest request) {
        return mapper.map(request, Author.class);
    }

    public AuthorResponse toAuthorResponse(Author entity) {
        return mapper.map(entity, AuthorResponse.class);
    }

    public List<AuthorResponse> toAuthorResponseList(List<Author> list) {
        return list.stream()
                .map(this::toAuthorResponse)
                .collect(Collectors.toList());
    }

    public Author updateAuthorFromRequest(AuthorRequest request, Author entity) {
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        mapper.map(request, entity);
        return entity;
    }

}
