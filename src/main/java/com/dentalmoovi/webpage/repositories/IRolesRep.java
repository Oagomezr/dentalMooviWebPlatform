package com.dentalmoovi.webpage.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dentalmoovi.webpage.models.Roles;

@Repository
public interface IRolesRep extends JpaRepository<Roles,Long>{
    public Optional<Roles> findByNameRole(@Param("name_role") String nameRole);
}
