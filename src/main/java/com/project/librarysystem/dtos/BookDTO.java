package com.project.librarysystem.dtos;

import lombok.Data;

import com.project.librarysystem.models.Author;

@Data
public class BookDTO {

    private Long id;
    private String title;
    private AuthorDTO author;

}
