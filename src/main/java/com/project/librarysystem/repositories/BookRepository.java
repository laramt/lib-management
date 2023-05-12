package com.project.librarysystem.repositories;

import com.project.librarysystem.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByTitleAndAuthor(String title, String author);


}
