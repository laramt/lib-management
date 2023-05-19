package com.project.librarysystem.dtos;

import com.project.librarysystem.models.BookCopy;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BookCopyDTO {

    private Long id;
    private String title;
    private String author;
    private List<BookCopy> copies = new ArrayList<>();

}
