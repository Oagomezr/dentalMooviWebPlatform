package com.dentalmoovi.webpage.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.dentalmoovi.webpage.models.Users;

public interface IUsersRep extends JpaRepository<Users,Long>{
    boolean existsByUsername(@Param("username") String username);
    boolean existsByEmail(@Param("email") String email);
    Optional<Users> findByUsername(@Param("username") String username);
}
