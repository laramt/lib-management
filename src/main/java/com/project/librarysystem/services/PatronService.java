package com.project.librarysystem.services;

import com.project.librarysystem.models.Patron;

import java.util.List;

public interface PatronService {

    Patron registerNewPatron(Patron patron);
    List<Patron> findAll();
    Patron findById(Long id);
    Patron update(Long id, Patron patron);

}
