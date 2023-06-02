package com.project.librarysystem.services.impl;

import com.project.librarysystem.dtos.BookDTO;
import com.project.librarysystem.exceptions.ResourceNotFoundException;
import com.project.librarysystem.mappers.BookMapper;
import com.project.librarysystem.models.Book;
import com.project.librarysystem.repositories.BookRepository;
import com.project.librarysystem.services.BookService;

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

    @Override
    public Book getOrCreateBook(Book book) {
      
        String title = book.getTitle();
        String author = book.getAuthor();

        if (!repository.existsByTitleAndAuthor(title, author)) {
            repository.save(book);
        } else {
            book = repository.findByTitleAndAuthor(title, author);
        }

        return book;
    }

}
