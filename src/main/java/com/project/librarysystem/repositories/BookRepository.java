package com.project.librarysystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.librarysystem.models.Book;


public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByTitleAndAuthor(String title, Long id);
    Book findByTitleAndAuthorId(String title, Long id);

}
