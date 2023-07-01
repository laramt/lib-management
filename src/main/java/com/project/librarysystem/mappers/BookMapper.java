package com.project.librarysystem.mappers;

import com.project.librarysystem.dtos.BookDTO;
import com.project.librarysystem.models.Book;

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
        Book book = mapper.map(dto, Book.class);
        return book;
    }

   public BookDTO toBookDTO(Book entity) {
    BookDTO book = mapper.map(entity, BookDTO.class);
    return book;
}

    public List<BookDTO> toBookDTOList(List<Book> books) {
        return books.stream()
                .map(this::toBookDTO)
                .collect(Collectors.toList());
    }

}
