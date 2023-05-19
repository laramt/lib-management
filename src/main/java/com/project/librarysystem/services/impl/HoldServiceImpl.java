package com.project.librarysystem.services.impl;

import com.project.librarysystem.enums.BookStatus;
import com.project.librarysystem.exceptions.ResourceNotAvailableException;
import com.project.librarysystem.exceptions.ResourceNotFoundException;
import com.project.librarysystem.models.BookCopy;
import com.project.librarysystem.models.Hold;
import com.project.librarysystem.models.Patron;
import com.project.librarysystem.repositories.BookCopyRepository;
import com.project.librarysystem.repositories.HoldRepository;
import com.project.librarysystem.repositories.PatronRepository;
import com.project.librarysystem.services.HoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class HoldServiceImpl implements HoldService {
    @Autowired
    HoldRepository holdRepository;
    @Autowired
    BookCopyRepository bookCopyRepository;
    @Autowired
    PatronRepository patronRepository;

   private static final int LOAN_DAYS = 14;

   private static final BigDecimal DAILY_FEE = new BigDecimal(1.20);

   @Transactional
   public Hold checkout(Long patronId, Long bookCopyId){

        Optional<BookCopy> obj = bookCopyRepository.findById(bookCopyId);
        BookCopy bookCopy = obj.orElseThrow(() -> new ResourceNotFoundException("Book with" + bookCopyId + " not found"));

        if(bookCopyRepository.isBookCopyAvailable(bookCopyId)) {
            bookCopy.setStatus(BookStatus.CHECKED_OUT);
            bookCopyRepository.save(bookCopy);
       }
        else {
            throw new ResourceNotAvailableException("Book is not available");
        }

        Optional<Patron> pt = patronRepository.findById(patronId);
        Patron patron = pt.orElseThrow(() -> new ResourceNotFoundException("Patron with" + patronId + "not found"));

        Hold hold = Hold.builder()
                .bookCopy(bookCopy)
                .patron(patron)
                .dueDate(LocalDate.now(ZoneId.of("UTC")).plusDays(LOAN_DAYS))
                .build();

        return holdRepository.save(hold);
    }

    @Transactional
    public Hold devolution(Long id){

        Hold hold = findById(id);
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
        BookCopy book = hold.getBookCopy();
        book.setStatus(BookStatus.AVAILABLE);
        bookCopyRepository.save(book);
        hold.setReturned(true);

        return holdRepository.save(hold);
    }

    public List<Hold> findAll() {
        return holdRepository.findAll();
    }

    public Hold findById(Long id) {
        Optional<Hold> hd = holdRepository.findById(id);
        Hold hold = hd.orElseThrow(() -> new ResourceNotFoundException("Hold with id " + id + " not found."));
        return hold;
    }
}
