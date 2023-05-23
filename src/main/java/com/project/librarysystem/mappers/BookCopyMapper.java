package com.project.librarysystem.mappers;

import com.project.librarysystem.dtos.BookCopyDTO;
import com.project.librarysystem.models.BookCopy;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookCopyMapper {

    private final ModelMapper mapper;

    public BookCopy toBookCopy(BookCopyDTO dto) {
        return mapper.map(dto, BookCopy.class);
    }

    public BookCopyDTO toBookCopyDTO(BookCopy entity) {
        return mapper.map(entity, BookCopyDTO.class);
    }

    public List<BookCopyDTO> toBookCopyDTOList(List<BookCopy> patrons) {
        return patrons.stream()
                .map(this::toBookCopyDTO)
                .collect(Collectors.toList());
    }


}
