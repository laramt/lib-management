package com.project.librarysystem.mappers;

import com.project.librarysystem.dtos.HoldDTO;
import com.project.librarysystem.models.Hold;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HoldMapper {

    private final ModelMapper mapper;
    private final BookCopyMapper bookCopyMapper;
    private final PatronMapper patronMapper;

    public Hold toHold(HoldDTO dto) {
        Hold hold = mapper.map(dto, Hold.class);
        hold.setBookCopy(bookCopyMapper.toBookCopy(dto.getBookCopy()));
        hold.setPatron(patronMapper.toPatron(dto.getPatron()));
        return hold;
    }

    public HoldDTO toHoldDTO(Hold entity) {
        HoldDTO hold = mapper.map(entity, HoldDTO.class);
        hold.setBookCopy(bookCopyMapper.toBookCopyDTO(entity.getBookCopy()));
        hold.setPatron(patronMapper.toPatronDTO(entity.getPatron()));
        return hold;
    }

    public List<HoldDTO> toHoldDTOList(List<Hold> patrons) {
        return patrons.stream()
                .map(this::toHoldDTO)
                .collect(Collectors.toList());
    }

}
