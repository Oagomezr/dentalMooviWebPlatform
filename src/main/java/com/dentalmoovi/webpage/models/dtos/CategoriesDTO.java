package com.dentalmoovi.webpage.models.dtos;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CategoriesDTO {

    @EqualsAndHashCode.Include
    private List<String> categoryAndParents;
    private List<CategoriesDTO> childrenCategories;

}
