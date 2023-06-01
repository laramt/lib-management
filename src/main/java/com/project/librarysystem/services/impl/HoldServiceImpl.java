package com.project.librarysystem.services.impl;

import com.project.librarysystem.dtos.HoldDTO;
import com.project.librarysystem.exceptions.ResourceNotAvailableException;
import com.project.librarysystem.exceptions.ResourceNotFoundException;
import com.project.librarysystem.mappers.HoldMapper;
import com.project.librarysystem.models.BookCopy;
import com.project.librarysystem.models.Hold;
import com.project.librarysystem.models.Patron;
import com.project.librarysystem.models.enums.BookStatus;
import com.project.librarysystem.repositories.BookCopyRepository;
import com.project.librarysystem.repositories.HoldRepository;
import com.project.librarysystem.repositories.PatronRepository;
import com.project.librarysystem.services.EmailService;
import com.project.librarysystem.services.HoldService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HoldServiceImpl implements HoldService {

    private final HoldRepository holdRepository;
    private final BookCopyRepository bookCopyRepository;
    private final PatronRepository patronRepository;
    private final EmailService emailService;
    private final HoldMapper mapper;

    private static final int LOAN_DAYS = 14;

    private static final BigDecimal DAILY_FEE = new BigDecimal(1.20);

    @Override
    @Transactional
    public HoldDTO borrow(Long patronId, Long bookCopyId) {

        // verify if book copy and patron exists
        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId)
            .orElseThrow(() -> new ResourceNotFoundException("Book with" + bookCopyId + " not found"));

        Patron patron = patronRepository.findById(patronId)
            .orElseThrow(() -> new ResourceNotFoundException("Patron with" + patronId + "not found"));

        // verify if book is available    
        if (bookCopyRepository.isBookCopyAvailable(bookCopyId)) {
            bookCopy.setStatus(BookStatus.CHECKED_OUT);
            bookCopyRepository.save(bookCopy);
        } else {
            throw new ResourceNotAvailableException("Book is not available");
        }
      
        // build, save it to repository and send informational email
        Hold hold = Hold.builder()
                .bookCopy(bookCopy)
                .patron(patron)
                .dueDate(LocalDate.now(ZoneId.of("UTC")).plusDays(LOAN_DAYS))
                .build();

        holdRepository.save(hold);
        emailService.sendBorrowedBook(hold);
        return mapper.toHoldDTO(hold);
    }


    @Override
    @Transactional
    public HoldDTO devolution(Long id) {

        // verify if hold exists
        Hold hold = holdRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hold with id " + id + " not found."));

        // set devolution date
        LocalDate checkInDate = LocalDate.now();
        
        // check if devolution is late and set fee
        int lateDays = Period.between(hold.getDueDate(), checkInDate).getDays();
        BigDecimal fee;
        
        if (lateDays > 0) {
            fee = BigDecimal.valueOf(lateDays).multiply(DAILY_FEE);
        } else {
            fee = BigDecimal.ZERO;
        }

        // set book copy and save to repository
        BookCopy book = hold.getBookCopy();
        book.setStatus(BookStatus.AVAILABLE);
        bookCopyRepository.save(book);

        // set hold and save it to repository
        hold.setCheckInDate(checkInDate);
        hold.setLateFee(fee.setScale(2, RoundingMode.HALF_EVEN));
        hold.setReturned(true);
        holdRepository.save(hold);
        
        return mapper.toHoldDTO(hold);
    }

    @Override
    public List<HoldDTO> findAll() {
        return mapper.toHoldDTOList(holdRepository.findAll());
    }

    @Override
    public HoldDTO findById(Long id) {
        Hold hold = holdRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hold with id " + id + " not found."));
        return mapper.toHoldDTO(hold);
    }
}
