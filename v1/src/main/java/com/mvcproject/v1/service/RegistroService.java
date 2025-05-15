package com.mvcproject.v1.service;

import com.mvcproject.v1.model.RegistroModel;
import java.util.List;
import java.util.Optional;

public interface RegistroService {
    List<RegistroModel> findAll();
    Optional<RegistroModel> findById(Long id);
    RegistroModel save(RegistroModel registro);
    void deleteById(Long id);
    
}
