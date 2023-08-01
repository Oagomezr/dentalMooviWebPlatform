package com.dentalmoovi.webpage.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dentalmoovi.webpage.models.Categories;

public interface ICategoriesRep extends JpaRepository<Categories, Long>{
    List<Categories> findByParentCategoryIsNull();
}
