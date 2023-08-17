package com.project.librarysystem.services.impl;

import com.project.librarysystem.dtos.request.BookCopyRequest;
import com.project.librarysystem.dtos.response.BookCopyResponse;
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
    public BookCopyResponse newBookCopy(BookCopyRequest request) {
        
        BookCopy bookCopy = mapper.toBookCopy(request);
    
        // verify publisher
        Publisher publisher = publisherRepository.findById(request.getPublisherId())
                    .orElseThrow(() -> new ResourceNotFoundException("Publisher with id " + request.getPublisherId() + " not found."));

        // verify book
        Book book = bookRepository.findById(request.getBookId())
                    .orElseThrow(() -> new ResourceNotFoundException("Book with id " + request.getBookId() + " not found."));


        // build and save on repository
        bookCopy = BookCopy.builder()
                    .book(book)
                    .publisher(publisher)
                    .isbn(bookCopy.getIsbn())
                    .language(bookCopy.getLanguage())
                    .status(BookStatus.AVAILABLE)
                    .yearPublished(bookCopy.getYearPublished())
                    .build();
            
        bookCopy = repository.save(bookCopy);
        return mapper.toBookCopyResponse(bookCopy);
    }

    @Override
    public List<BookCopyResponse> findAll() {
        return mapper.toBookCopyResponseList(repository.findAll());
    }

    @Override
    public BookCopyResponse findById(Long id) {
        BookCopy bookCopy = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found."));

        return mapper.toBookCopyResponse(bookCopy);
    }

    @Override
    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found."));
        repository.deleteById(id);
    }

    @Override
    public BookCopyResponse update(Long id, BookCopyRequest request) {
        BookCopy bookCopy = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found."));

        bookCopy = mapper.updateBookCopyFromRequest(request, bookCopy);

        repository.save(bookCopy);
        return mapper.toBookCopyResponse(bookCopy);
    }

}
