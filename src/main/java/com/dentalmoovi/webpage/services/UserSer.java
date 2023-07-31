package com.dentalmoovi.webpage.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dentalmoovi.webpage.dtos.RoleDTO;
import com.dentalmoovi.webpage.dtos.UserDTO;
import com.dentalmoovi.webpage.exceptions.DataNotFoundException;
import com.dentalmoovi.webpage.models.Roles;
import com.dentalmoovi.webpage.models.Users;
import com.dentalmoovi.webpage.repositories.IRolesRep;
import com.dentalmoovi.webpage.repositories.IUsersRep;
@Service
public class UserSer implements InterfaceUserSer{

    @Autowired
    private IUsersRep usersRep;
    @Autowired
    private IRolesRep rolesRep;

    @Override
    public List<UserDTO> getAllUsers() {
        List<Users> users = usersRep.findAll();
        return users.stream().map(this::convertUserToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        class CreateUser{

            /* ¡¡PLEASE PAY CLOSE ATTENTION ONLY THIS "createUser()" METHOD TO UNDERSTAND THIS SERVICE!! */ 
            UserDTO createUser(){ 
                checkEmailExists(userDTO.getEmail());
                Users newUser = insertUnrelatedData(userDTO); //add non foreign key data
                Roles defaultRole = rolesRep.findByNameRole("USER").orElseThrow(() -> new DataNotFoundException(notFoundMessage));
                newUser.getRoles().add(defaultRole); //add default role --> USER
                String hashedPassword = new BCryptPasswordEncoder().encode(userDTO.getPassword()); //Encrypt the password
                newUser.setPassword(hashedPassword); //set encrypt pssword
                Users userCreated = usersRep.save(newUser); // add complete user to the database
                usersRep.flush();
                userDTO.setRoles(new HashSet<>());
                userDTO.getRoles().add(new RoleDTO(defaultRole.getIdRole(), "USER", null)); //ADD default role
                userDTO.setIdUser(userCreated.getIdUser()); // get id generated from database and store inside DTO
                return userDTO;
            }
        
            private Users insertUnrelatedData(UserDTO userDTO){
                return new Users(  
                    null, userDTO.getFirstName(), userDTO.getLastName(), 
                    userDTO.getEmail(), userDTO.getCelPhone(), userDTO.getBirthday(), userDTO.getGender(), null, new HashSet<>());
            }
        }

        CreateUser innerClass = new CreateUser();
        return innerClass.createUser();
    }

    @Override
    public UserDTO getUserById(Long idUser) {
        Users user = usersRep.findById(idUser).orElseThrow(() -> new DataNotFoundException(notFoundMessage));
        return convertUserToDTO(user);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        Users user = usersRep.findByEmail(email).orElseThrow(() -> new DataNotFoundException(notFoundMessage));
        return convertUserToDTO(user);
    }

    @Override
    public UserDTO updateUser(Long idUser, UserDTO userDTO) {
        Users user = usersRep.findById(idUser).orElseThrow(() -> new DataNotFoundException(notFoundMessage));
        userDTO.setIdUser(idUser);
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setCelPhone(userDTO.getCelPhone());
        user.setBirthday(userDTO.getBirthday());
        user.setGender(userDTO.getGender());
        user = usersRep.save(user);
        return userDTO;
    }

    @Override
    public void deleteUser(Long id) {
        Users user = usersRep.findById(id).orElseThrow(() -> new DataNotFoundException(notFoundMessage));
        usersRep.delete(user);
    }

    @Override
    public boolean checkEmailExists(String value) {
        return usersRep.existsByEmail(value).orElseThrow(() -> new IllegalArgumentException("Email no exists"));
    }

    private UserDTO convertUserToDTO(Users user){
        UserDTO userDTO = getUserFromDatabase(user);

        Set<Roles> roles = user.getRoles();
        if(roles !=null && !roles.isEmpty()){
            Hibernate.initialize(roles);
            userDTO.setRoles(getAllUserRolesFromDatabase(roles));
        }

        return userDTO;
    }

    private UserDTO getUserFromDatabase(Users user){
        return new UserDTO(user.getIdUser(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getCelPhone(), user.getBirthday(), user.getGender(), null, null);
    }

    private Set<RoleDTO> getAllUserRolesFromDatabase(Set<Roles> roles){
        Set<RoleDTO> rolesDTO = new HashSet<>();
        for(Roles role : roles){
            RoleDTO roleDTO = getRoleFromDatabase(role);
            rolesDTO.add(roleDTO);
        }
        return rolesDTO;
    }
    private RoleDTO getRoleFromDatabase(Roles role){
        return new RoleDTO(role.getIdRole(), role.getNameRole(), null);
    }

    private String notFoundMessage = "User not found";

}
