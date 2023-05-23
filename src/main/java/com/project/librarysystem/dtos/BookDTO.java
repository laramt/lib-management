package com.project.librarysystem.dtos;

import com.project.librarysystem.models.BookCopy;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class BookDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String author;
    private List<BookCopy> copies = new ArrayList<>();

}
