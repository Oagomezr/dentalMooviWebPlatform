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

        //To find all categories, it's necesary find parent categories first
        List<Categories> parentCategories = categoriesRep.findByParentCategoryIsNullOrderByName();
        //We have DTOs to send the categories
        List<CategoriesDTO> categoriesDTO = new ArrayList<>();

        for (Categories parentCategory : parentCategories) {
            CategoriesDTO parentCategoryDTO = new CategoriesDTO();

            //We pretend to send our category with its respective subcategories
            parentCategoryDTO.setCategoryAndParents(List.of(parentCategory.getName()));
            parentCategoryDTO.setChildrenCategories(getSubCategories(parentCategory, List.of(parentCategory.getName())));
            categoriesDTO.add(parentCategoryDTO);
        }

        /*One of the best practices in programming is not send List or Arrays as response,
        instead Objects as response*/
        CategoriesResponse categoriesResponse = new CategoriesResponse();
        categoriesResponse.setData(categoriesDTO);
        return categoriesResponse;
    }

    //It's is a recursive function with the aim of loop all subcategories's subcategories
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
                CategoriesDTO childDTO = new CategoriesDTO();
                childDTO.setCategoryAndParents(idAndParents);
                childDTO.setChildrenCategories(getSubCategories(child, idAndParents));
                childrenDTO.add(childDTO);
            }
            return childrenDTO;
        }
    }
}