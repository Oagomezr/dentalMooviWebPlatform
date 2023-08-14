package com.dentalmoovi.webpage.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ImagesDTO {

    private String name;
    private String contentType;

    private String imageBase64;
}
