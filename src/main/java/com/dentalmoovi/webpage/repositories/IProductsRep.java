package com.dentalmoovi.webpage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dentalmoovi.webpage.models.Products;

public interface IProductsRep extends JpaRepository<Products,Long>{
    
}
