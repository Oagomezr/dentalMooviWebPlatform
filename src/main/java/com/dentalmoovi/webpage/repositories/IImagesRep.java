package com.dentalmoovi.webpage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dentalmoovi.webpage.models.entities.Images;

public interface IImagesRep extends JpaRepository<Images, Long>{
    
}
