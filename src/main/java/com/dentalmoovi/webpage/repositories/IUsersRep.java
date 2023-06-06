package com.dentalmoovi.webpage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dentalmoovi.webpage.models.Users;

public interface IUsersRep extends JpaRepository<Users,Long>{
    
}
