package com.project.librarysystem.services.impl;

import com.project.librarysystem.dtos.BookCopyDTO;
import com.project.librarysystem.exceptions.ResourceNotFoundException;
import com.project.librarysystem.mappers.BookCopyMapper;
import com.project.librarysystem.models.BookCopy;
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
      
        // check if isbn exists
        if (repository.findByIsbn(bookCopy.getIsbn()) != null) {
            throw new RuntimeException("Book with isbn already exists.");
        }
        
        // build and save on repository
        bookCopy = BookCopy.builder()
                    .book(bookService.getOrCreateBook(bookCopy.getBook()))
                    .publisher(publisherService.getOrCreatPublisher(bookCopy.getPublisher()))
                    .isbn(bookCopy.getIsbn())
                    .status(BookStatus.AVAILABLE)
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
