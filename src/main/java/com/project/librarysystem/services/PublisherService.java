package com.project.librarysystem.services;


import java.util.List;

import com.project.librarysystem.dtos.PublisherDTO;

public interface PublisherService {
    
    PublisherDTO findById(Long id);
    List<PublisherDTO> findAll();

}
