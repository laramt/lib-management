package com.project.librarysystem.domain.services.impl;

import com.project.librarysystem.domain.services.BookService;
import com.project.librarysystem.api.dtos.BookDTO;
import com.project.librarysystem.exceptions.ResourceNotFoundException;
import com.project.librarysystem.domain.mappers.BookMapper;
import com.project.librarysystem.domain.models.Book;
import com.project.librarysystem.domain.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final BookMapper mapper;

    @Override
    public List<BookDTO> findAll() {
        List<Book> books = repository.findAll();
        return mapper.toBookDTOList(books);
    }

    @Override
    public BookDTO findById(Long id) {
        Book book = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Book with id " + id + " not found."));
        return  mapper.toBookDTO(book);
    }

}
