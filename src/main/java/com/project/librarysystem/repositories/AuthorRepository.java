package com.project.librarysystem.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.librarysystem.models.Author;


public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByNameContaining(String name);
    
}
