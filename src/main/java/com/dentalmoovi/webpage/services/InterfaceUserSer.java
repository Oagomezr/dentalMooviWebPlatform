package com.dentalmoovi.webpage.services;

import java.util.List;

import com.dentalmoovi.webpage.models.dtos.UserDTO;

public interface InterfaceUserSer {
    public List<UserDTO> getAllUsers();
    public UserDTO createUser(UserDTO userDTO);
    public UserDTO getUserById(Long idUser);
    public UserDTO getUserByEmail(String token);
    public boolean checkEmailExists(String email);
    public UserDTO updateUser(Long id, UserDTO userDTO);
    public void deleteUser(Long id);
}
