package com.dentalmoovi.webpage.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dentalmoovi.webpage.models.entities.Products;

public interface IProductsRep extends JpaRepository<Products,Long>{
    @Query("SELECT p FROM Products p WHERE p.category.name = :categoryName")
    List<Products> findByCategoryName(@Param("categoryName") String categoryName);

    @Query("SELECT count(p) FROM Products p WHERE p.category.name = :categoryName")
    int countByCategoryName(@Param("categoryName") String categoryName);

    Optional<Products> findByNameProduct(@Param("nameProduct") String nameProduct);
    List<Products> findByNameProductContainingIgnoreCase(String name);
    List<Products> findTop7ByNameProductContainingIgnoreCase(String name);

    @Query("SELECT p.numberUpdates FROM Products p WHERE p.nameProduct = :nameProduct")
    String findNumberUpdatesByNameProduct(String nameProduct);
}
