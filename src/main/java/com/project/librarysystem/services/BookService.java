package com.project.librarysystem.services;

import com.project.librarysystem.models.Book;
import com.project.librarysystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public Optional<Book> findById (UUID id){
        return bookRepository.findById(id);
    }

    public void delete (UUID id){
        bookRepository.deleteById(id);
    }

}
