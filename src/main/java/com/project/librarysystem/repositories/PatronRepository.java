package com.project.librarysystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.librarysystem.models.Patron;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Long> {

}
