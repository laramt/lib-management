package com.project.librarysystem.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.project.librarysystem.models.Author;


public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByName(String name);
    
}
