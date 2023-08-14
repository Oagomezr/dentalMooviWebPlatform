package com.dentalmoovi.webpage.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dentalmoovi.webpage.models.Products;

public interface IProductsRep extends JpaRepository<Products,Long>{
    @Query("SELECT p FROM Products p WHERE p.category.id = :categoryId")
    List<Products> findByCategoryId(@Param("categoryId") Long categoryId);
}
