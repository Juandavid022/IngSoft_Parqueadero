package com.mvcproject.v1.service;

import com.mvcproject.v1.model.TipoVehiculoModel;
import java.util.List;
import java.util.Optional;

public interface TipoVehiculoService {
    List<TipoVehiculoModel> findAll();
    Optional<TipoVehiculoModel> findById(Long id);
    TipoVehiculoModel save(TipoVehiculoModel tipoVehiculo);
    void deleteById(Long id);
}
