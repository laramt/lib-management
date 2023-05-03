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

    public Optional<Book> findById (Long id){
        return bookRepository.findById(id);
    }

    public void delete (Long id){
        bookRepository.deleteById(id);
    }

    public void update (Long id, Book book){
        Book bk = bookRepository.getById(id);
        bk.setTitle(book.getTitle());
        bk.setAuthor(book.getAuthor());
        bk.setIsbn(book.getIsbn());
        bk.setPublisher(book.getPublisher());
        bk.setYearPublished(book.getYearPublished());
        bk.setStatus(book.getStatus());

        bookRepository.save(bk);
    }

}
