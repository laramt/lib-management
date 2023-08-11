package com.project.librarysystem.mappers;

import com.project.librarysystem.dtos.request.PublisherRequest;
import com.project.librarysystem.dtos.response.PublisherResponse;
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

    public Publisher toPublisher(PublisherRequest dto) {
        return mapper.map(dto, Publisher.class);
    }

    public PublisherResponse toPublisherResponse(Publisher entity) {
        return mapper.map(entity, PublisherResponse.class);
    }

    public List<PublisherResponse> toPublisherResponseList(List<Publisher> list) {
        return list.stream()
                .map(this::toPublisherResponse)
                .collect(Collectors.toList());
    }

}
