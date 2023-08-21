package com.dentalmoovi.webpage.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dentalmoovi.webpage.models.entities.Categories;

public interface ICategoriesRep extends JpaRepository<Categories, Long>{
    List<Categories> findByParentCategoryIsNullOrderByName();
    List<Categories> findByParentCategoryOrderByName(@Param("parent_category") Categories parentCategory);
    Optional<Categories> findByName(@Param("name") String name);
    
    @Query("SELECT MAX(e.id) FROM Categories e")
    long findMaxId();

    @Query("SELECT SUM(e.numberUpdates) FROM Categories e")
    long countUpdates();

    @Query("SELECT c.checkProduct FROM Categories c WHERE c.name = ?1")
    String findCheckProductosByName(String name);
}
