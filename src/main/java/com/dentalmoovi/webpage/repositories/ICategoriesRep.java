package com.dentalmoovi.webpage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dentalmoovi.webpage.models.Categories;

public interface ICategoriesRep extends JpaRepository<Categories, Long>{
    
}
