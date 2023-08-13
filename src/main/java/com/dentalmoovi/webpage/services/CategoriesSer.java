package com.dentalmoovi.webpage.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dentalmoovi.webpage.dtos.CategoriesDTO;
import com.dentalmoovi.webpage.models.Categories;
import com.dentalmoovi.webpage.repositories.ICategoriesRep;

@Service
public class CategoriesSer {

    @Autowired
    private ICategoriesRep categoriesRep;

    public String checkUpdate(){
        return String.valueOf(categoriesRep.findMaxId() + categoriesRep.count());
    }

    public Set<CategoriesDTO> getAllCategories() {
        List<Categories> parentCategories = categoriesRep.findByParentCategoryIsNull();
        Set<CategoriesDTO> parentCategoriesDTO = new HashSet<>();
        for (Categories parentCategory : parentCategories) {
            CategoriesDTO parentCategoryDTO = new CategoriesDTO(parentCategory.getIdCategory() ,parentCategory.getName(), 0, getSubCategories(parentCategory, 1));
            parentCategoriesDTO.add(parentCategoryDTO);
        }
        return parentCategoriesDTO;
    }

    private Set<CategoriesDTO> getSubCategories(Categories category, int lvl) {
        int readjust = (lvl - 1) % 4 + 1;
        List<Categories> children = categoriesRep.findByParentCategory(category);
        Set<CategoriesDTO> childrenDTO = new HashSet<>();
        if(children.isEmpty()){
            return childrenDTO;
        }else{
            for (Categories child : children) {
                CategoriesDTO childDTO = new CategoriesDTO(child.getIdCategory() ,child.getName(), readjust, getSubCategories(child, lvl+1));
                childrenDTO.add(childDTO);
            }
            return childrenDTO;
        }
        
    }

}