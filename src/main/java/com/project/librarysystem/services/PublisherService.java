package com.project.librarysystem.services;


import java.util.List;

import com.project.librarysystem.dtos.request.PublisherRequest;
import com.project.librarysystem.dtos.response.PublisherResponse;

public interface PublisherService {
    
    PublisherResponse findById(Long id);
    List<PublisherResponse> findAll();
    PublisherResponse insert(PublisherRequest request);
    List<PublisherResponse> findByName(String name);

}
