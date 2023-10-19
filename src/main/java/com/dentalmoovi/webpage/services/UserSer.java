package com.dentalmoovi.webpage.services;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dentalmoovi.webpage.models.dtos.RoleDTO;
import com.dentalmoovi.webpage.models.dtos.UserDTO;
import com.dentalmoovi.webpage.models.entities.Roles;
import com.dentalmoovi.webpage.models.entities.Users;
import com.dentalmoovi.webpage.repositories.IRolesRep;
import com.dentalmoovi.webpage.repositories.IUsersRep;

@Service
public class UserSer implements InterfaceUserSer{

    @Autowired
    private IUsersRep usersRep;
    @Autowired
    private IRolesRep rolesRep;
    @Autowired
    private EmailSer emailSer;
    @Autowired
    private CacheSer cacheSer;

    Random random = new Random();

    @Override
    public List<UserDTO> getAllUsers() {
        List<Users> users = usersRep.findAll();
        return users.stream().map(this::convertUserToDTO).collect(Collectors.toList());
    }
    
    public void sendEmailNotification(String email) {
        
        //Generate randomNumber
        int randomNumber = random.nextInt(1000000);

        //generate a format to add zeros to the left in case randomNumber < 100000
        String formattedNumber = String.format("%06d", randomNumber);

        String subject = "Codigo de confirmación";
        String body =   "Dental Moovi recibió una solicitud de registro.\n\n"+
                        "El codigo de confirmación es: "+ formattedNumber;
        
        cacheSer.addToOrUpdateRegistrationCache(email, formattedNumber);

        try {
            emailSer.sendEmail(email, subject, body);
        } catch (Exception e) {
            // Manage the exception in case it cannot send the email
            e.printStackTrace();
        }
    }

    public String createUser(UserDTO userDTO) throws RuntimeException {

        class CreateUser{

            /* ¡¡PLEASE PAY CLOSE ATTENTION ONLY THIS "createUser()" METHOD TO UNDERSTAND THIS SERVICE!! */ 
            String createUser() throws RuntimeException{ 
                if(checkEmailExists(userDTO.getEmail())) throw new RuntimeException("That user already exist");
                String code = cacheSer.getFromRegistrationCache(userDTO.getEmail());
                //filter to know if user confirm the email
                if(!userDTO.getConfirmCode().equals(code)) throw new RuntimeException("That code is incorrect");
                Users newUser = insertUnrelatedData(userDTO); //add non foreign key data
                Roles defaultRole = rolesRep.findByNameRole("USER").orElseThrow(() -> new RuntimeException(notFoundMessage));
                newUser.getRoles().add(defaultRole); //add default role --> USER
                String hashedPassword = new BCryptPasswordEncoder().encode(userDTO.getPassword()); //Encrypt the password
                newUser.setPassword(hashedPassword); //set encrypt password
                usersRep.save(newUser); // add complete user to the database
                usersRep.flush(); //avoid errors during save data process
                return "Usuario creado con exito";
            }
        
            //This method is only use to insert unrelated data
            private Users insertUnrelatedData(UserDTO userDTO){
                Users user = new Users();
                user.setFirstName(userDTO.getFirstName());
                user.setLastName(userDTO.getLastName());
                user.setEmail(userDTO.getEmail());
                user.setCelPhone(userDTO.getCelPhone());
                user.setBirthday(userDTO.getBirthday());
                user.setGender(userDTO.getGender());
                return user;
            }
        }

        CreateUser innerClass = new CreateUser();
        return innerClass.createUser();
    }

    @Override
    public UserDTO getUserById(Long idUser) {
        Users user = usersRep.findById(idUser).orElseThrow(() -> new RuntimeException(notFoundMessage));
        return convertUserToDTO(user);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        Users user = usersRep.findByEmail(email).orElseThrow(() -> new RuntimeException(notFoundMessage));
        return convertUserToDTO(user);
    }

    @Override
    public UserDTO updateUser(Long idUser, UserDTO userDTO) {
        Users user = usersRep.findById(idUser).orElseThrow(() -> new RuntimeException(notFoundMessage));
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
        Users user = usersRep.findById(id).orElseThrow(() -> new RuntimeException(notFoundMessage));
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
        UserDTO userDTO = new UserDTO();
        userDTO.setIdUser(user.getIdUser());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setCelPhone(user.getCelPhone());
        userDTO.setBirthday(user.getBirthday());
        userDTO.setGender(user.getGender());
        return userDTO;
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
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setIdRole(role.getIdRole());
        roleDTO.setNameRole(role.getNameRole());
        return roleDTO;
    }

    private String notFoundMessage = "User not found";
}
