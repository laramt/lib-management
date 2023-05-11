package com.project.librarysystem.repositories;

import com.project.librarysystem.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT CASE WHEN (b.status = AVAILABLE) THEN TRUE ELSE FALSE END FROM Book b WHERE b.id = :bookId")
    boolean isBookAvailable(@Param("bookId") Long bookId);

}
