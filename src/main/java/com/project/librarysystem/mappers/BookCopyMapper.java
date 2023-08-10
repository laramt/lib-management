package com.project.librarysystem.mappers;

import com.project.librarysystem.dtos.request.BookCopyRequest;
import com.project.librarysystem.dtos.response.BookCopyResponse;
import com.project.librarysystem.models.BookCopy;

import lombok.RequiredArgsConstructor;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookCopyMapper {

    private final ModelMapper mapper;

    public BookCopy toBookCopy(BookCopyRequest request) {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return mapper.map(request, BookCopy.class);

    }

    public BookCopyResponse toBookCopyResponse(BookCopy entity) {
        return mapper.map(entity, BookCopyResponse.class);

    }

    public List<BookCopyResponse> toBookCopyResponseList(List<BookCopy> list) {
        return list.stream()
                .map(this::toBookCopyResponse)
                .collect(Collectors.toList());
    }

        public BookCopy updateBookCopyFromRequest(BookCopyRequest request, BookCopy entity) {
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        mapper.map(request, entity);
        return entity;
    }


}

