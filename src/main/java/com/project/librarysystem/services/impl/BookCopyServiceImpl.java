package com.project.librarysystem.services.impl;

import com.project.librarysystem.dtos.BookCopyDTO;
import com.project.librarysystem.exceptions.InvalidInputException;
import com.project.librarysystem.exceptions.ResourceNotFoundException;
import com.project.librarysystem.mappers.BookCopyMapper;
import com.project.librarysystem.models.Book;
import com.project.librarysystem.models.BookCopy;
import com.project.librarysystem.models.Publisher;
import com.project.librarysystem.models.enums.BookStatus;
import com.project.librarysystem.repositories.BookCopyRepository;
import com.project.librarysystem.services.BookCopyService;
import com.project.librarysystem.services.BookService;
import com.project.librarysystem.services.PublisherService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookCopyServiceImpl implements BookCopyService {

    private final BookCopyRepository repository;
    private final BookService bookService;
    private final PublisherService publisherService;
    private final BookCopyMapper mapper;

    @Override
    public BookCopyDTO newBookCopy(BookCopyDTO dto) {
        BookCopy bookCopy = mapper.toBookCopy(dto);
    
        // check publisher
        Publisher publisher = publisherService.findByName(bookCopy.getPublisher().getName());
        if(publisher == null){
            throw new   InvalidInputException("Publisher with name \"" + bookCopy.getPublisher().getName() + "\" does not exists.");
        }

        // check book
        Book book = bookService.findByTitleAndAuthor(bookCopy.getBook().getTitle(), bookCopy.getBook().getAuthor());
        if(book == null){
            throw new   InvalidInputException("Book \"" + bookCopy.getBook().getTitle() + " - " + bookCopy.getBook().getAuthor() + "\" does not exists.");
        }

        // build and save on repository
        bookCopy = BookCopy.builder()
                    .book(book)
                    .publisher(publisher)
                    .isbn(bookCopy.getIsbn())
                    .status(BookStatus.AVAILABLE)
                    .yearPublished(bookCopy.getYearPublished())
                    .build();
            
        bookCopy = repository.save(bookCopy);
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
