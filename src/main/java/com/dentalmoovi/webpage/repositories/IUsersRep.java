package com.dentalmoovi.webpage.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.dentalmoovi.webpage.models.entities.Users;

public interface IUsersRep extends JpaRepository<Users,Long>{
    Optional<Boolean> existsByEmail(@Param("email") String email);
    Optional<Users> findByEmail(@Param("email") String email);
}
