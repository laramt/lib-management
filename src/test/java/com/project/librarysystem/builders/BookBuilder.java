package com.project.librarysystem.builders;

import com.project.librarysystem.models.Book;

public class BookBuilder {

    public static Book nullIdBook(){
        Book book = new Book(
                null,
                "Han Kang",
                "Atos Humanos"
        );
        return book;
    }

    public static Book aBook(){
        Book book = new Book(
                5L,
                "Han Kang",
                "Atos Humanos"
        );
        return book;
    }

}
