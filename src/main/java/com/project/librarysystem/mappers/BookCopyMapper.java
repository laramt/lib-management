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
    BookCopy bookCopy = mapper.map(dto, BookCopy.class);
    return bookCopy;
}

    public BookCopyDTO toBookCopyDTO(BookCopy entity) {
    BookCopyDTO bookCopy = mapper.map(entity, BookCopyDTO.class);
    return bookCopy;
    }

    public List<BookCopyDTO> toBookCopyDTOList(List<BookCopy> patrons) {
        return patrons.stream()
                .map(this::toBookCopyDTO)
                .collect(Collectors.toList());
    }


}

