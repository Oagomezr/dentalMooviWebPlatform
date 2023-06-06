package com.dentalmoovi.webpage.services;

import java.util.List;

import com.dentalmoovi.webpage.dtos.UserDTO;

public interface InterfaceUserSer {
    public List<UserDTO> getAllUsers();
    public UserDTO createUser(UserDTO userDTO);
    public UserDTO getUser(Long idUser);
    public boolean checkValueExists(String field ,String value);
    public UserDTO updateUser(Long id, UserDTO userDTO);
    public void deleteUser(Long id);
}
