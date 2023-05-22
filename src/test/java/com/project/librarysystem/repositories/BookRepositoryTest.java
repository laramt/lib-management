package com.project.librarysystem.repositories;

import com.project.librarysystem.builders.BookBuilder;
import com.project.librarysystem.models.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

@DataJpaTest
@DirtiesContext
public class BookRepositoryTest {

    @Autowired
    BookRepository repository;

    @Test
    @DisplayName("Save should persist book with autoincrement when id is null")
    public void saveShouldPersistBookWithAutoincrementWhenIdIsNull (){
        //set up
        Book book = BookBuilder.nullIdBook();

        //action
        Book retrivedBook = repository.save(book);

        //assertions
        Assertions.assertNotNull(book.getId());
        Assertions.assertEquals(book.getTitle(), retrivedBook.getTitle());
        Assertions.assertEquals(book.getTitle(), retrivedBook.getTitle());
        Assertions.assertEquals(book.getAuthor(), retrivedBook.getAuthor());
    }

    @Test
    @DisplayName("findById should return non empty optional when id exists")
    public void findByIdShouldReturnNonEmptyOptionalWhenIdExists(){
        // set up
        Book book = BookBuilder.nullIdBook();
        repository.save(book);

        // action
        Optional<Book> optional = repository.findById(book.getId());

        // assertions
        Assertions.assertTrue(optional.isPresent());
    }

    @Test
    @DisplayName("findById should return empty optional when id does not exists")
    public void findByIdShouldReturnEmptyOptionalWhenIdDoesNotExist (){
        // set up
        Book book = BookBuilder.nullIdBook();
        repository.save(book);

        // action
        Long nonExistingId = book.getId() + 1L;
        Optional<Book> optional = repository.findById(nonExistingId);

        // assertions
        Assertions.assertFalse(optional.isPresent());
    }

}
