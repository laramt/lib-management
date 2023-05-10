package com.project.librarysystem.services;

import com.project.librarysystem.enums.BookStatus;
import com.project.librarysystem.models.Book;
import com.project.librarysystem.models.Hold;
import com.project.librarysystem.models.Patron;
import com.project.librarysystem.repositories.BookRepository;
import com.project.librarysystem.repositories.HoldRepository;
import com.project.librarysystem.repositories.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.*;
import java.time.temporal.ChronoUnit;
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

   private static final int LOAN_DAYS = 14;

   private static final BigDecimal DAILY_FEE = new BigDecimal(1.20);

    public Hold checkout(Long patronId, Long bookId){

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
                .checkout(LocalDate.now(ZoneId.of("UTC")))
                .dueDate(LocalDate.now(ZoneId.of("UTC")).plusDays(LOAN_DAYS))
                .build();

        return holdRepository.save(hold);
    }

    public Hold devolution(Long id){

        Optional<Hold> hd = findById(id);
        Hold hold = hd.orElseThrow(() -> new RuntimeException("Hold not found"));

        LocalDate checkin = LocalDate.now();
        BigDecimal fee;

        BigDecimal lateDays = BigDecimal.valueOf(ChronoUnit.DAYS.between(hold.getDueDate(), checkin));

        if (lateDays.compareTo(BigDecimal.ZERO) > 0) {
            fee = lateDays.multiply(DAILY_FEE);
        }
        else {
            fee = new BigDecimal(0.00);
        }

        hold.setCheckIn(checkin);
        hold.setLateFee(fee.setScale(2, RoundingMode.HALF_EVEN));
        hold.getBook().setStatus(BookStatus.AVAILABLE);
        hold.setReturned(true);

        return holdRepository.save(hold);
    }

    public List<Hold> findAll() {
        return holdRepository.findAll();
    }

    public Optional<Hold> findById(Long id) {
        return holdRepository.findById(id);
    }
}
