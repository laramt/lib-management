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
    private final BookMapper bookMapper;
    private final PublisherMapper publisherMapper;

    public BookCopy toBookCopy(BookCopyDTO dto) {
    BookCopy bookCopy = mapper.map(dto, BookCopy.class);
    bookCopy.setBook(bookMapper.toBook(dto.getBook()));
    bookCopy.setPublisher(publisherMapper.toPublisher(dto.getPublisher()));
    return bookCopy;
}

    public BookCopyDTO toBookCopyDTO(BookCopy entity) {
    BookCopyDTO bookCopy = mapper.map(entity, BookCopyDTO.class);
    bookCopy.setBook(bookMapper.toBookDTO(entity.getBook()));
    bookCopy.setPublisher(publisherMapper.toPublisherDTO(entity.getPublisher()));
    return bookCopy;
    }

    public List<BookCopyDTO> toBookCopyDTOList(List<BookCopy> patrons) {
        return patrons.stream()
                .map(this::toBookCopyDTO)
                .collect(Collectors.toList());
    }


}

