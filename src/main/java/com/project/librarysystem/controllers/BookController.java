package com.project.librarysystem.controllers;

import com.project.librarysystem.models.Book;
import com.project.librarysystem.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/new-book")
    public ResponseEntity<Object> saveBook(@RequestBody Book book){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(book));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> list = bookService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

}
