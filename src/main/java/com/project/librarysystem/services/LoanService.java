package com.project.librarysystem.services;

import java.util.List;

import com.project.librarysystem.dtos.response.LoanResponse;

public interface LoanService {

    LoanResponse borrow(Long patronId, Long bookCopyId);
    LoanResponse devolution(Long id);
    List<LoanResponse> findAll();
    LoanResponse findById(Long id);
    
}
