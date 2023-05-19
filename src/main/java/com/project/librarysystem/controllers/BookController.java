package com.project.librarysystem.controllers;

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

    @PostMapping("/new-book")
    public ResponseEntity<Book> saveBook(@RequestBody Book book){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(book));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> list = bookService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBookById(@PathVariable Long id){
        Book book = bookService.findById(id);
        if(!Optional.of(book).isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book does not exists");
        }

        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBookById(@PathVariable Long id){
        Book book = bookService.findById(id);
        if(!Optional.of(book).isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book does not exists");
        }

        bookService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Book deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable Long id, @RequestBody Book book){
        Book bk = bookService.findById(id);
        if(!Optional.of(bk).isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book does not exists");
        }

        Book updatedBook = bookService.update(id, book);
        return ResponseEntity.status(HttpStatus.OK).body(updatedBook);
    }

}
