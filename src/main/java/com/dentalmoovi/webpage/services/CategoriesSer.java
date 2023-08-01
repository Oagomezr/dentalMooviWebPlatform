package com.dentalmoovi.webpage.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dentalmoovi.webpage.models.Categories;
import com.dentalmoovi.webpage.repositories.ICategoriesRep;

@Service
public class CategoriesSer {

    @Autowired
    private ICategoriesRep categoriesRep;

    public List<String> getParentCategories(){
        List<Categories> parentCategories = categoriesRep.findByParentCategoryIsNull();
        return parentCategories.stream().map(Categories::getName).collect(Collectors.toList());
    }
}
