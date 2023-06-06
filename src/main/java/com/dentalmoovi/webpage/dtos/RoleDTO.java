package com.dentalmoovi.webpage.dtos;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class RoleDTO {
    private Long idRole;
    private String nameRole;
    private Set<UserDTO> users = new HashSet<>();
}
