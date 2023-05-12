package com.project.librarysystem.services;

import com.project.librarysystem.enums.BookStatus;
import com.project.librarysystem.exceptions.ResourceNotFoundException;
import com.project.librarysystem.models.Book;
import com.project.librarysystem.models.BookCopy;
import com.project.librarysystem.repositories.BookCopyRepository;
import com.project.librarysystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookCopyService {

    @Autowired
    BookCopyRepository bookCopyRepository;
    @Autowired
    BookRepository bookRepository;

    public BookCopy newBookCopy(BookCopy bookCopy){

        // Check if book is null
        Book book = bookCopy.getBook();
        if (book == null) {
            throw new ResourceNotFoundException("Book cannot be null.");
        }

        // Check if title or author is null
        String title = bookCopy.getBook().getTitle();
        String author = bookCopy.getBook().getAuthor();
        if (title == null || author == null){
            throw new ResourceNotFoundException("Author or title cannot be null.");
        }

        // Check if book already exists
        Book existingBook = bookRepository.findByTitleAndAuthor(title, author);
        if (existingBook == null) {
            if (bookCopyRepository.findByIsbn(bookCopy.getIsbn()) != null) {
                throw new RuntimeException("Book with isbn already exists.");
            }

            bookCopy.setStatus(BookStatus.AVAILABLE);
            bookRepository.save(book);
        }
        else {
            book = existingBook;
        }

        bookCopy.setBook(book);
        return bookCopyRepository.save(bookCopy);
    }

    public List<BookCopy> findAll(){
        return bookCopyRepository.findAll();
    }

    public BookCopy findById (Long id){
        Optional<BookCopy> pt = bookCopyRepository.findById(id);
        BookCopy bookCopy = pt.orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found."));
        return bookCopy;

    }

    public void delete (Long id){
        findById(id);
        bookCopyRepository.deleteById(id);
    }

    public BookCopy update (Long id, BookCopy bookCopy){
        BookCopy bk = findById(id);

        bk.setBook(bookCopy.getBook());
        bk.setIsbn(bookCopy.getIsbn());
        bk.setPublisher(bookCopy.getPublisher());
        bk.setYearPublished(bookCopy.getYearPublished());
        bk.setStatus(bookCopy.getStatus());

        bookCopyRepository.save(bk);
        return null;
    }

}
