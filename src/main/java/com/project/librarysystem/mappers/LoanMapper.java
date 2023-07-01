package com.project.librarysystem.mappers;

import com.project.librarysystem.dtos.request.LoanRequest;
import com.project.librarysystem.dtos.response.LoanResponse;
import com.project.librarysystem.models.Loan;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LoanMapper {

    private final ModelMapper mapper;

    public Loan toHold(LoanRequest dto) {
        return mapper.map(dto, Loan.class);
    }

    public LoanResponse toLoanResponse(Loan entity) {
        return mapper.map(entity, LoanResponse.class);
    }

    public List<LoanResponse> toLoanResponseList(List<Loan> list) {
        return list.stream()
                .map(this::toLoanResponse)
                .collect(Collectors.toList());
    }

}


