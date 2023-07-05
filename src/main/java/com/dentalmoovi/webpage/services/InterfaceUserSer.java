package com.dentalmoovi.webpage.services;

import java.util.List;

import com.dentalmoovi.webpage.dtos.UserDTO;

public interface InterfaceUserSer {
    public List<UserDTO> getAllUsers();
    public UserDTO createUser(UserDTO userDTO);
    public UserDTO getUserById(Long idUser);
    public UserDTO getUserByJwt(String token);
    public boolean checkValueExists(String field ,String value);
    public UserDTO updateUser(Long id, UserDTO userDTO);
    public void deleteUser(Long id);
}
