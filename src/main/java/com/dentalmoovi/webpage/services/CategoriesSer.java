package com.dentalmoovi.webpage.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.dentalmoovi.webpage.models.dtos.CategoriesDTO;
import com.dentalmoovi.webpage.models.entities.Categories;
import com.dentalmoovi.webpage.models.reponses.CategoriesResponse;
import com.dentalmoovi.webpage.repositories.ICategoriesRep;

@Service
public class CategoriesSer {

    @Autowired
    private ICategoriesRep categoriesRep;

    public String checkUpdate(){
        return String.valueOf(categoriesRep.findMaxId() + categoriesRep.countUpdates());
    }

    @Cacheable(cacheNames = "getAllCategories")
    public CategoriesResponse getAllCategories() {
        List<Categories> parentCategories = categoriesRep.findByParentCategoryIsNullOrderByName();
        List<CategoriesDTO> parentCategoriesDTO = new ArrayList<>();
        for (Categories parentCategory : parentCategories) {
            CategoriesDTO parentCategoryDTO = new CategoriesDTO(List.of(parentCategory.getName()), getSubCategories(parentCategory, List.of(parentCategory.getName())));
            parentCategoriesDTO.add(parentCategoryDTO);
        }
        return new CategoriesResponse(parentCategoriesDTO);
    }

    private List<CategoriesDTO> getSubCategories(Categories category, List<String> parents) {
        List<Categories> children = categoriesRep.findByParentCategoryOrderByName(category);
        
        List<CategoriesDTO> childrenDTO = new ArrayList<>();
        if(children.isEmpty()){
            return childrenDTO;
        }else{
            for (Categories child : children) {
                List<String> idAndParents = new ArrayList<>();
                idAndParents.add(child.getName());
                idAndParents.addAll(parents);
                CategoriesDTO childDTO = new CategoriesDTO(idAndParents, getSubCategories(child, idAndParents));
                childrenDTO.add(childDTO);
            }
            return childrenDTO;
        }
        
    }

}