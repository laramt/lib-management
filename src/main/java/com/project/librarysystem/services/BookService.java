package com.project.librarysystem.services;

import com.project.librarysystem.exceptions.ResourceNotFoundException;
import com.project.librarysystem.models.Book;
import com.project.librarysystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService{

    @Autowired
    BookRepository bookRepository;

    public Book save(Book book){
        return bookRepository.save(book);
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book findById (Long id){
        Optional<Book> pt = bookRepository.findById(id);
        Book book = pt.orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found."));
        return book;

    }

    public void delete (Long id){
        findById(id);
        bookRepository.deleteById(id);
    }

    public Book update (Long id, Book book){
        Book bk = findById(id);

        bk.setTitle(book.getTitle());
        bk.setAuthor(book.getAuthor());

        bookRepository.save(bk);
        return bk;
    }

}
