package com.project.librarysystem.mappers;

import com.project.librarysystem.dtos.request.BookRequest;
import com.project.librarysystem.dtos.response.BookResponse;
import com.project.librarysystem.models.Book;

import lombok.RequiredArgsConstructor;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookMapper {

    private final ModelMapper mapper;


      public Book toBook(BookRequest request) {
        Book book = mapper.map(request, Book.class);
        return book;
    }

   public BookResponse toBookResponse(Book entity) {
    BookResponse book = mapper.map(entity, BookResponse.class);
    return book;
}

    public List<BookResponse> toBookResponseList(List<Book> books) {
        return books.stream()
                .map(this::toBookResponse)
                .collect(Collectors.toList());
    }

    public Book updateBookFromRequest(BookRequest request, Book entity) {
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        mapper.map(request, entity);
        return entity;
    }

}
