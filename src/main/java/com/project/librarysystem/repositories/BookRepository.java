package com.project.librarysystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.librarysystem.models.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitleAndAuthor(String title, String author);

}
