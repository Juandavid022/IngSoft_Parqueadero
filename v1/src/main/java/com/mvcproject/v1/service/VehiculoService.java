package com.mvcproject.v1.service;

import java.util.List;
import java.util.Optional;

import com.mvcproject.v1.model.VehiculoModel;


public interface VehiculoService {
    List<VehiculoModel> findAll();
    Optional<VehiculoModel> findById(Long id);
    VehiculoModel save(VehiculoModel vehiculo);
    void deleteById(Long id);
}
