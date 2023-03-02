package com.project.librarysystem.controllers;

import com.project.librarysystem.models.Book;
import com.project.librarysystem.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> list = bookService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

}
