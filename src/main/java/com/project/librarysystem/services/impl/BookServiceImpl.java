package com.project.librarysystem.services.impl;

import com.project.librarysystem.dtos.BookDTO;
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
    public BookDTO insert(BookDTO dto) {
        Book book = mapper.toBook(dto);

        if ( book == null || book.getTitle() == null) {
            throw new ResourceNotFoundException("Book, title or author cannot be null.");
        }

        Author author = authorRepository.findByName(book.getAuthor().getName());
        if (author == null) {
        throw new ResourceNotFoundException("Author with not found.");
        }

       if (repository.existsByTitleAndAuthor(book.getTitle(), author.getId())) {
            throw new InvalidInputException("Book \"" + book.getTitle() + " - " + author.getName() + "\" already exists.");
        }

        repository.save(book);
        return mapper.toBookDTO(book);
    }


    @Override
    public Book findByTitleAndAuthor(String title, String authorName) {
        Author author = authorRepository.findByName(authorName);

        if (author == null) {
        throw new ResourceNotFoundException("Author with name \"" + authorName + "\" not found.");
        }

    return repository.findByTitleAndAuthorId(title, author.getId());
    }

}
