package com.project.librarysystem.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.librarysystem.dtos.request.PublisherRequest;
import com.project.librarysystem.dtos.response.PublisherResponse;
import com.project.librarysystem.exceptions.ResourceNotFoundException;
import com.project.librarysystem.mappers.PublisherMapper;
import com.project.librarysystem.models.Publisher;
import com.project.librarysystem.repositories.PublisherRepository;
import com.project.librarysystem.services.PublisherService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService{

    private final PublisherRepository repository;
    private final PublisherMapper mapper;


    @Override
    public PublisherResponse findById(Long id) {
        Publisher publisher = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found."));

        return mapper.toPublisherResponse(publisher);
    }


    @Override
    public List<PublisherResponse> findAll() {
        return mapper.toPublisherResponseList(repository.findAll());
    }


    @Override
    public PublisherResponse insert(PublisherRequest request) {
    
        if (request == null || request.getName() == null) {
            throw new ResourceNotFoundException("Publisher cannot be null.");
        }
        Publisher publisher = mapper.toPublisher(request);
        repository.save(publisher);
        return mapper.toPublisherResponse(publisher);
    }


    @Override
    public PublisherResponse findByName(String name) { 
        Publisher publisher = repository.findByName(name);
        return mapper.toPublisherResponse(publisher);
    }
    
}
