package com.project.librarysystem.domain.services.impl;

import com.project.librarysystem.domain.services.BookCopyService;
import com.project.librarysystem.api.dtos.BookCopyDTO;
import com.project.librarysystem.domain.models.enums.BookStatus;
import com.project.librarysystem.exceptions.ResourceNotFoundException;
import com.project.librarysystem.domain.mappers.BookCopyMapper;
import com.project.librarysystem.domain.models.Book;
import com.project.librarysystem.domain.models.BookCopy;
import com.project.librarysystem.domain.repositories.BookCopyRepository;
import com.project.librarysystem.domain.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookCopyServiceImpl implements BookCopyService {

    private final BookCopyRepository repository;
    private final BookRepository bookRepository;
    private final BookCopyMapper mapper;

    public BookCopyDTO newBookCopy(BookCopyDTO dto) {

        BookCopy bookCopy = mapper.toBookCopy(dto);

        // Check if book is null
        Book book = bookCopy.getBook();
        if (book == null) {
            throw new ResourceNotFoundException("Book cannot be null.");
        }

        // Check if title or author is null
        String title = bookCopy.getBook().getTitle();
        String author = bookCopy.getBook().getAuthor();
        if (title == null || author == null) {
            throw new ResourceNotFoundException("Author or title cannot be null.");
        }

        // Check if book already exists
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

        bookCopy.setBook(book);
        repository.save(bookCopy);
        return mapper.toBookCopyDTO(bookCopy);
    }

    public List<BookCopyDTO> findAll() {
        return mapper.toBookCopyDTOList(repository.findAll());
    }

    public BookCopyDTO findById(Long id) {
        BookCopy bookCopy = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found."));

        return mapper.toBookCopyDTO(bookCopy);
    }

    public void delete(Long id) {
        BookCopy bookCopy = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found."));
        repository.deleteById(id);
    }

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
