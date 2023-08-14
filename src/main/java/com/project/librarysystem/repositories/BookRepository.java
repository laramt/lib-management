package com.project.librarysystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.librarysystem.models.Book;


public interface BookRepository extends JpaRepository<Book, Long> {

@Query("SELECT COUNT(b) > 0 FROM Book b WHERE b.title = :title AND b.author.id = :authorId")
boolean existsByTitleAndAuthor(@Param("title") String title, @Param("authorId") Long authorId);    

Book findByTitleAndAuthorId(String title, Long id);

}
