package com.project.librarysystem.builders;

import com.project.librarysystem.enums.BookStatus;
import com.project.librarysystem.models.Book;
import com.project.librarysystem.models.BookCopy;

public class BookCopyBuilder {

    public static BookCopy nullIdBookCopy(){
        BookCopy bookCopy = new BookCopy(
                null,
                book(),
                "9786456320123",
                "Toda Via",
                2013,
                BookStatus.AVAILABLE
        );
        return bookCopy;
    }

    public static Book book(){
        Book book = new Book(
                null,
                "Han Kang",
                "Atos Humanos"
        );
        return book;
    }

}
