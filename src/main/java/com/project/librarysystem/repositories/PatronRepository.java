package com.project.librarysystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.librarysystem.models.Patron;

public interface PatronRepository extends JpaRepository<Patron, Long> {

}
