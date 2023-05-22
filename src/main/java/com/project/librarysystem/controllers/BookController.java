package com.project.librarysystem.controllers;

import com.project.librarysystem.dtos.BookDTO;
import com.project.librarysystem.models.Book;
import com.project.librarysystem.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;


    @GetMapping("/all")
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        List<BookDTO> list = bookService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBookById(@PathVariable Long id){
        BookDTO book = bookService.findById(id);
        if(!Optional.of(book).isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book does not exists");
        }

        return ResponseEntity.status(HttpStatus.OK).body(book);
    }


}
