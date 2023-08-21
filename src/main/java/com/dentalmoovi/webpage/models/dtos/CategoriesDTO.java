package com.dentalmoovi.webpage.models.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoriesDTO {

    private List<String> categoryAndParents;
    private List<CategoriesDTO> childrenCategories;

}
