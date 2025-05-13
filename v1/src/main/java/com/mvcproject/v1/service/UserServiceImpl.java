package com.mvcproject.v1.service;

import com.mvcproject.v1.model.UserModel;
import com.mvcproject.v1.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    // Constructor para inyectar el repositorio
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserModel> listar() {
        // Implementar lógica de listado de usuarios
        return (List<UserModel>) userRepository.findAll();
    }

    @Override
    public UserModel listarId(Long id) {
        // Implementar lógica de búsqueda por ID
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public UserModel saveUser(UserModel user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<UserModel> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public long countUsers() {
        return userRepository.count();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Usamos Optional para manejar la ausencia de un usuario de manera más segura
        Optional<UserModel> optionalUser = userRepository.findByUsername(username);

        // Si no se encuentra el usuario, lanzamos una excepción
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        UserModel userModel = optionalUser.get();

        // Devolvemos el UserDetails con los datos del usuario
        return User.builder()
                .username(userModel.getUsername())
                .password(userModel.getPassword())  // Aquí se asume que la contraseña ya está cifrada
                .roles("USER")  // Puedes personalizar los roles si es necesario
                .build();
    }
}
