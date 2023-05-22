package com.project.librarysystem.repositories;

import com.project.librarysystem.builders.BookCopyBuilder;
import com.project.librarysystem.models.BookCopy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

@DataJpaTest
public class BookCopyRepositoryTest {

    @Autowired
    BookCopyRepository repository;

    @Test
    @DisplayName("Save should persist book copy with autoincrement when id is null")
    public void saveShouldPersistBookCopyWithAutoincrementWhenIdIsNull (){
        //set up
        BookCopy bookCopy = BookCopyBuilder.nullIdBookCopy();

        //action
        BookCopy retrivedBookCopy = repository.save(bookCopy);

        //assertions
        Assertions.assertNotNull(bookCopy.getId());
        Assertions.assertEquals(bookCopy.getIsbn(), retrivedBookCopy.getIsbn());
        Assertions.assertEquals(bookCopy.getPublisher(), retrivedBookCopy.getPublisher());
        Assertions.assertEquals(bookCopy.getYearPublished(), retrivedBookCopy.getYearPublished());
    }

    @Test
    @DisplayName("findById should return non empty optional when id exists")
    public void findByIdShouldReturnNonEmptyOptionalWhenIdExists(){
        // set up
        BookCopy bookCopy = BookCopyBuilder.nullIdBookCopy();
        repository.save(bookCopy);

        // action
        Optional<BookCopy> optional = repository.findById(bookCopy.getId());

        // assertions
        Assertions.assertTrue(optional.isPresent());
    }

    @Test
    @DisplayName("findById should return empty optional when id does not exists")
    public void findByIdShouldReturnEmptyOptionalWhenIdDoesNotExist (){
        // set up
        BookCopy bookCopy = BookCopyBuilder.nullIdBookCopy();
        repository.save(bookCopy);

        // action
        Long nonExistingId = bookCopy.getId() + 1L;
        Optional<BookCopy> optional = repository.findById(nonExistingId);

        // assertions
        Assertions.assertFalse(optional.isPresent());
    }

}
