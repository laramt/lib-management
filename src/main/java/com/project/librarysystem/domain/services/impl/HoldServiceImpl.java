package com.project.librarysystem.domain.services.impl;

import com.project.librarysystem.domain.services.EmailService;
import com.project.librarysystem.domain.services.HoldService;
import com.project.librarysystem.api.dtos.HoldDTO;
import com.project.librarysystem.domain.models.enums.BookStatus;
import com.project.librarysystem.exceptions.ResourceNotAvailableException;
import com.project.librarysystem.exceptions.ResourceNotFoundException;
import com.project.librarysystem.domain.mappers.HoldMapper;
import com.project.librarysystem.domain.models.BookCopy;
import com.project.librarysystem.domain.models.Hold;
import com.project.librarysystem.domain.models.Patron;
import com.project.librarysystem.domain.repositories.BookCopyRepository;
import com.project.librarysystem.domain.repositories.HoldRepository;
import com.project.librarysystem.domain.repositories.PatronRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

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

        Optional<BookCopy> obj = bookCopyRepository.findById(bookCopyId);
        BookCopy bookCopy = obj.orElseThrow(() -> new ResourceNotFoundException("Book with" + bookCopyId + " not found"));

        if (bookCopyRepository.isBookCopyAvailable(bookCopyId)) {
            bookCopy.setStatus(BookStatus.CHECKED_OUT);
            bookCopyRepository.save(bookCopy);
        } else {
            throw new ResourceNotAvailableException("Book is not available");
        }

        Optional<Patron> pt = patronRepository.findById(patronId);
        Patron patron = pt.orElseThrow(() -> new ResourceNotFoundException("Patron with" + patronId + "not found"));

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

        Hold hold = holdRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hold with id " + id + " not found."));

        LocalDate checkin = LocalDate.now();
        BigDecimal fee;

        BigDecimal lateDays = BigDecimal.valueOf(ChronoUnit.DAYS.between(hold.getDueDate(), checkin));

        if (lateDays.compareTo(BigDecimal.ZERO) > 0) {
            fee = lateDays.multiply(DAILY_FEE);
        } else {
            fee = new BigDecimal(0.00);
        }

        hold.setCheckInDate(checkin);
        hold.setLateFee(fee.setScale(2, RoundingMode.HALF_EVEN));
        BookCopy book = hold.getBookCopy();
        book.setStatus(BookStatus.AVAILABLE);
        bookCopyRepository.save(book);
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
