package com.dentalmoovi.webpage.models.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;



@Entity
@Table
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Roles {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Long idRole;

    @Column(name = "name_role")
    @EqualsAndHashCode.Include
    private String nameRole;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<Users> users = new HashSet<>();

    @Override
    public String toString() {
        return nameRole;
    }
    
    
}
