package com.mvcproject.v1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.mvcproject.v1.model.UserModel;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Long> {

    // Método que devuelve un Optional, lo que es una mejor práctica.
    Optional<UserModel> findByUsername(String username);

    // Este método ya no es necesario, puedes eliminarlo
    // UserModel findByUsername(String username); 

    // Método para verificar si existe un usuario con ese nombre de usuario
    boolean existsByUsername(String username);
}
