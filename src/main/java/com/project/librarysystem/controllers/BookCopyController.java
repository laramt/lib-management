package com.project.librarysystem.controllers;

import com.project.librarysystem.models.BookCopy;
import com.project.librarysystem.services.BookCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book-copies")
public class BookCopyController {

    @Autowired
    BookCopyService bookCopyService;

    @PostMapping("/new-book")
    public ResponseEntity<BookCopy> newBookCopy(@RequestBody BookCopy bookCopy){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookCopyService.newBookCopy(bookCopy));
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookCopy>> getAllBookCopies(){
        List<BookCopy> list = bookCopyService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBookCopyById(@PathVariable Long id){
        BookCopy bookCopy = bookCopyService.findById(id);
        if(!Optional.of(bookCopy).isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("BookCopy does not exists");
        }

        return ResponseEntity.status(HttpStatus.OK).body(bookCopy);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBookCopyById(@PathVariable Long id){
        BookCopy bookCopy = bookCopyService.findById(id);
        if(!Optional.of(bookCopy).isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("BookCopy does not exists");
        }

        bookCopyService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("BookCopy deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBookCopyCopy(@PathVariable Long id, @RequestBody BookCopy bookCopy){
        BookCopy bk = bookCopyService.findById(id);
        if(!Optional.of(bk).isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("BookCopy does not exists");
        }

        BookCopy updatedBookCopy = bookCopyService.update(id, bookCopy);
        return ResponseEntity.status(HttpStatus.OK).body(updatedBookCopy);
    }

}
