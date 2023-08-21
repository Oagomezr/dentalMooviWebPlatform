package com.dentalmoovi.webpage.models.reponses;

import java.util.List;

import com.dentalmoovi.webpage.models.dtos.CategoriesDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoriesResponse {
    private List<CategoriesDTO> data;
}
