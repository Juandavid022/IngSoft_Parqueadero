package com.mvcproject.v1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.mvcproject.v1.model.UserModel;

@Service
public interface UserService {
    
    // Método para listar todos los usuarios
    List<UserModel> listar();

    // Método para listar un usuario por ID
    UserModel listarId(Long id);

    // Método para guardar o actualizar un usuario
    UserModel saveUser(UserModel user);

    // Método para buscar un usuario por su nombre de usuario
    Optional<UserModel> findUserByUsername(String username);

    // Método para verificar si el nombre de usuario ya existe
    boolean usernameExists(String username);

    // Método para eliminar un usuario por su identificador
    void deleteUserById(Long id);

    // Método para contar el número total de usuarios
    long countUsers();

    UserDetails loadUserByUsername(String username);
}
