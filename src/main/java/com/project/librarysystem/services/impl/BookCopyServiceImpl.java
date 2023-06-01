package com.project.librarysystem.services.impl;

import com.project.librarysystem.dtos.BookCopyDTO;
import com.project.librarysystem.exceptions.ResourceNotFoundException;
import com.project.librarysystem.mappers.BookCopyMapper;
import com.project.librarysystem.models.Book;
import com.project.librarysystem.models.BookCopy;
import com.project.librarysystem.models.Publisher;
import com.project.librarysystem.models.enums.BookStatus;
import com.project.librarysystem.repositories.BookCopyRepository;
import com.project.librarysystem.repositories.BookRepository;
import com.project.librarysystem.repositories.PublisherRepository;
import com.project.librarysystem.services.BookCopyService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookCopyServiceImpl implements BookCopyService {

    private final BookCopyRepository repository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final BookCopyMapper mapper;

    @Override
    public BookCopyDTO newBookCopy(BookCopyDTO dto) {

        BookCopy bookCopy = mapper.toBookCopy(dto);

        // check if book is null
        Book book = bookCopy.getBook();
        if (book == null) {
            throw new ResourceNotFoundException("Book cannot be null.");
        }

        // check if title or author is null
        String title = book.getTitle();
        String author = book.getAuthor();
        if (title == null || author == null) {
            throw new ResourceNotFoundException("Author or title cannot be null.");
        }

        // check if book already exists
        Book existingBook = bookRepository.findByTitleAndAuthor(title, author);
        if (existingBook == null) {
            if (repository.findByIsbn(bookCopy.getIsbn()) != null) {
                throw new RuntimeException("Book with isbn already exists.");
            }

            bookCopy.setStatus(BookStatus.AVAILABLE);
            bookRepository.save(book);
        } else {
            book = existingBook;
        }

        // check if publisher is null
        Publisher publisher = bookCopy.getPublisher();
        if (publisher == null || publisher.getName() == null) {
            throw new ResourceNotFoundException("Publisher cannot be null.");
        }

        // check if publisher already exists
        Publisher existingPublisher = publisherRepository.findByName(publisher.getName());
        if (existingPublisher == null) {
            publisherRepository.save(publisher);
        } else {
            publisher = existingPublisher;
        }

        // save on repository
        bookCopy.setBook(book);
        bookCopy.setPublisher(publisher);
        repository.save(bookCopy);
        return mapper.toBookCopyDTO(bookCopy);
    }

    @Override
    public List<BookCopyDTO> findAll() {
        return mapper.toBookCopyDTOList(repository.findAll());
    }

    @Override
    public BookCopyDTO findById(Long id) {
        BookCopy bookCopy = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found."));

        return mapper.toBookCopyDTO(bookCopy);
    }

    @Override
    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found."));
        repository.deleteById(id);
    }

    @Override
    public BookCopyDTO update(Long id, BookCopyDTO dto) {
        BookCopy bookCopy = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found."));

        bookCopy.setBook(dto.getBook());
        bookCopy.setIsbn(dto.getIsbn());
        bookCopy.setPublisher(dto.getPublisher());
        bookCopy.setYearPublished(dto.getYearPublished());
        bookCopy.setStatus(dto.getStatus());

        repository.save(bookCopy);
        return mapper.toBookCopyDTO(bookCopy);
    }

}
