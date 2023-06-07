package com.dentalmoovi.webpage.dtos;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoleDTO {
    private Long idRole;
    private String nameRole;
    private Set<UserDTO> users = new HashSet<>();
}
