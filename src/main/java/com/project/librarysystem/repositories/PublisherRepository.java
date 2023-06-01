package com.project.librarysystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.librarysystem.models.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long>{
    
    Publisher findByName(String name);
    boolean existsByName(String name);

}
