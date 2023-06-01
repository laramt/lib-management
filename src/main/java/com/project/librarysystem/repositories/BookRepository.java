package com.project.librarysystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.librarysystem.models.Book;


public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitleAndAuthor(String title, String author);
    boolean existsByTitleAndAuthor(String title, String author);

}
