package com.dentalmoovi.webpage.models.reponses;

import java.util.List;

import com.dentalmoovi.webpage.models.dtos.CategoriesDTO;

import lombok.Data;

@Data
public class CategoriesResponse {
    private List<CategoriesDTO> data;
}
