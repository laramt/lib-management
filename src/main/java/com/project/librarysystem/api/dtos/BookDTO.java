package com.project.librarysystem.api.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class BookDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String author;

}
