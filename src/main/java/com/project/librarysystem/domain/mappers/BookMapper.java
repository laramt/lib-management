package com.project.librarysystem.domain.mappers;

import com.project.librarysystem.api.dtos.BookDTO;
import com.project.librarysystem.domain.models.Book;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookMapper {
    private final ModelMapper mapper;

    public Book toBook(BookDTO dto) {
        return mapper.map(dto, Book.class);
    }

    public BookDTO toBookDTO(Book entity) {
        return mapper.map(entity, BookDTO.class);
    }

    public List<BookDTO> toBookDTOList(List<Book> books) {
        return books.stream()
                .map(this::toBookDTO)
                .collect(Collectors.toList());
    }

}
