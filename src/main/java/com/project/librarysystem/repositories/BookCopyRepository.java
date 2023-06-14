package com.project.librarysystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.librarysystem.models.BookCopy;


public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {
    
    @Query("SELECT CASE WHEN (b.status = AVAILABLE) THEN TRUE ELSE FALSE END FROM BookCopy b WHERE b.id = :bookCopyId")
    boolean isBookCopyAvailable(@Param("bookCopyId") Long bookCopyId);

}
