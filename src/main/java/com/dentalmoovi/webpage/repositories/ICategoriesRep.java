package com.dentalmoovi.webpage.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dentalmoovi.webpage.models.Categories;

public interface ICategoriesRep extends JpaRepository<Categories, Long>{
    List<Categories> findByParentCategoryIsNull();
    List<Categories> findByParentCategory(@Param("parent_category") Categories parentCategory);
    Optional<Categories> findByName(@Param("name") String name);
    
    @Query("SELECT MAX(e.id) FROM Categories e")
    long findMaxId();

    long count();
}
