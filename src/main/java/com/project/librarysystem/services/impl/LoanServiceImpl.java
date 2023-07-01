package com.project.librarysystem.services.impl;

import com.project.librarysystem.dtos.response.LoanResponse;
import com.project.librarysystem.exceptions.ResourceNotAvailableException;
import com.project.librarysystem.exceptions.ResourceNotFoundException;
import com.project.librarysystem.mappers.LoanMapper;
import com.project.librarysystem.models.BookCopy;
import com.project.librarysystem.models.Loan;
import com.project.librarysystem.models.User;
import com.project.librarysystem.models.enums.BookStatus;
import com.project.librarysystem.repositories.BookCopyRepository;
import com.project.librarysystem.repositories.LoanRepository;
import com.project.librarysystem.repositories.UserRepository;
import com.project.librarysystem.services.EmailService;
import com.project.librarysystem.services.LoanService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanRepository repository;
    private final BookCopyRepository bookCopyRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final LoanMapper mapper;

    private static final int LOAN_DAYS = 14;

    private static final BigDecimal DAILY_FEE = new BigDecimal(1.20);

    @Override
    @Transactional
    public LoanResponse borrow(Long userId, Long bookCopyId) {

        // verify if book copy and patron exists
        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId)
            .orElseThrow(() -> new ResourceNotFoundException("Book with" + bookCopyId + " not found"));

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User with" + userId + "not found"));

        // verify if book is available    
        if (bookCopyRepository.isBookCopyAvailable(bookCopyId)) {
            bookCopy.setStatus(BookStatus.CHECKED_OUT);
            bookCopyRepository.save(bookCopy);
        } else {
            throw new ResourceNotAvailableException("Book is not available");
        }
      
        // build, save it to repository and send informational email
        Loan loan = Loan.builder()
                .bookCopy(bookCopy)
                .user(user)
                .dueDate(LocalDate.now().plusDays(LOAN_DAYS))
                .build();

        repository.save(loan);
        emailService.sendBorrowedBook(loan);
        return mapper.toLoanResponse(loan);
    }


    @Override
    @Transactional
    public LoanResponse devolution(Long id) {

        // verify if hold exists
        Loan loan = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan with id " + id + " not found."));

        // set devolution date
        LocalDate checkInDate = LocalDate.now();
        
        // check if devolution is late and set fee
        int lateDays = Period.between(loan.getDueDate(), checkInDate).getDays();
        BigDecimal fee;
        
        if (lateDays > 0) {
            fee = BigDecimal.valueOf(lateDays).multiply(DAILY_FEE);
        } else {
            fee = BigDecimal.ZERO;
        }

        // set book copy and save to repository
        BookCopy book = loan.getBookCopy();
        book.setStatus(BookStatus.AVAILABLE);
        bookCopyRepository.save(book);

        // set hold and save it to repository
        loan.setCheckInDate(checkInDate);
        loan.setLateFee(fee.setScale(2, RoundingMode.HALF_EVEN));
        loan.setReturned(true);
        repository.save(loan);
        
        return mapper.toLoanResponse(loan);
    }

    @Override
    public List<LoanResponse> findAll() {
        return mapper.toLoanResponseList(repository.findAll());
    }

    @Override
    public LoanResponse findById(Long id) {
        Loan loan = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan with id " + id + " not found."));
        return mapper.toLoanResponse(loan);
    }
}
