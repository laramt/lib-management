package com.project.librarysystem.mappers;

import com.project.librarysystem.dtos.PublisherDTO;
import com.project.librarysystem.models.Publisher;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PublisherMapper {

    private final ModelMapper mapper;

    public Publisher toPublisher(PublisherDTO dto) {
        return mapper.map(dto, Publisher.class);
    }

    public PublisherDTO toPublisherDTO(Publisher entity) {
        return mapper.map(entity, PublisherDTO.class);
    }

    public List<PublisherDTO> toPublisherDTOList(List<Publisher> list) {
        return list.stream()
                .map(this::toPublisherDTO)
                .collect(Collectors.toList());
    }

}
