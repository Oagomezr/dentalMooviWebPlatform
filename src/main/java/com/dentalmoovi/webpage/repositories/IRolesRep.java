package com.dentalmoovi.webpage.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.dentalmoovi.webpage.models.entities.Roles;

public interface IRolesRep extends JpaRepository<Roles,Long>{
    public Optional<Roles> findByNameRole(@Param("name_role") String nameRole);
}
