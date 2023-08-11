package com.project.librarysystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.librarysystem.models.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long>{
    
    List<Publisher> findByNameContaining(String name);

}