package com.project.librarysystem.domain.mappers;

import com.project.librarysystem.api.dtos.HoldDTO;
import com.project.librarysystem.domain.models.Hold;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HoldMapper {

    private final ModelMapper mapper;

    public Hold toHold(HoldDTO dto) {
        return mapper.map(dto, Hold.class);
    }

    public HoldDTO toHoldDTO(Hold entity) {
        return mapper.map(entity, HoldDTO.class);
    }

    public List<HoldDTO> toHoldDTOList(List<Hold> patrons) {
        return patrons.stream()
                .map(this::toHoldDTO)
                .collect(Collectors.toList());
    }

}
