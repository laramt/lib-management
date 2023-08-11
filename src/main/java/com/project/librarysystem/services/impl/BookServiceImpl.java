package com.project.librarysystem.services.impl;

import com.project.librarysystem.dtos.request.BookRequest;
import com.project.librarysystem.dtos.response.BookResponse;
import com.project.librarysystem.exceptions.InvalidInputException;
import com.project.librarysystem.exceptions.ResourceNotFoundException;
import com.project.librarysystem.mappers.BookMapper;
import com.project.librarysystem.models.Author;
import com.project.librarysystem.models.Book;
import com.project.librarysystem.repositories.AuthorRepository;
import com.project.librarysystem.repositories.BookRepository;
import com.project.librarysystem.services.BookService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final AuthorRepository authorRepository;
    private final BookMapper mapper;

    @Override
    public List<BookResponse> findAll() {
        List<Book> books = repository.findAll();
        return mapper.toBookResponseList(books);
    }

    @Override
    public BookResponse findById(Long id) {
        Book book = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Book with id " + id + " not found."));
        return  mapper.toBookResponse(book);
    }

    @Override
    public BookResponse insert(BookRequest request) {

        if (request.getTitle() == null || request.getTitle().isEmpty() || request.getTitle().isBlank()) {
            throw new ResourceNotFoundException("Title filds cannot be null, empty or blank.");
        }

        Author author = authorRepository.findById(request.getAuthorId())
                        .orElseThrow(() -> new ResourceNotFoundException("Author with id " + request.getAuthorId() + " not found."));
        

       if (repository.existsByTitleAndAuthor(request.getTitle(), author.getId())) {
            throw new InvalidInputException("Book \"" + request.getTitle() + " - " + author.getName() + "\" already exists.");
        }

        Book book = new Book();
        book.setAuthor(author);
        book = repository.save(mapper.toBook(request));
        return mapper.toBookResponse(book);
    }


}
