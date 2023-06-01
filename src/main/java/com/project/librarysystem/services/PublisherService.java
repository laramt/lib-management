package com.project.librarysystem.services;

import com.project.librarysystem.dtos.PublisherDTO;

public interface PublisherService {
    
    PublisherDTO findById(Long id);

}
