package com.project.librarysystem.services;

import com.project.librarysystem.enums.BookStatus;
import com.project.librarysystem.models.Book;
import com.project.librarysystem.models.Hold;
import com.project.librarysystem.models.Patron;
import com.project.librarysystem.repositories.BookRepository;
import com.project.librarysystem.repositories.HoldRepository;
import com.project.librarysystem.repositories.PatronRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HoldService {
    @Autowired
    HoldRepository holdRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    PatronRepository patronRepository;
    static final int loanDays = 14;

    public Hold checkout(UUID patronId, UUID bookId){

        Optional<Book> obj = bookRepository.findById(bookId);
        Book book = obj.orElseThrow(() -> new RuntimeException("Book not found"));

        if(book.getStatus().equals(BookStatus.AVAILABLE)) {
            book.setStatus(BookStatus.CHECKED_OUT);
       }
        else {
            throw new RuntimeException("Book is not available");
        }

        Optional<Patron> pt = patronRepository.findById(patronId);
        Patron patron = pt.orElseThrow(() -> new RuntimeException("Patron not found"));

        Hold hold = Hold.builder()
                .book(book)
                .patron(patron)
                .checkout(LocalDateTime.now(ZoneId.of("UTC")))
                .dueDate(Instant.now().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(loanDays))
                .build();

        return holdRepository.save(hold);
    }

    public List<Hold> findAll() {
        return holdRepository.findAll();
    }
}
