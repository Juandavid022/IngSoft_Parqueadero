package com.mvcproject.v1.service.impl;

import com.mvcproject.v1.model.TipoVehiculoModel;
import com.mvcproject.v1.repository.TipoVehiculoRepository;
import com.mvcproject.v1.service.TipoVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoVehiculoServiceImpl implements TipoVehiculoService {

    @Autowired
    private TipoVehiculoRepository repository;

    @Override
    public List<TipoVehiculoModel> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<TipoVehiculoModel> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public TipoVehiculoModel save(TipoVehiculoModel tipoVehiculo) {
        return repository.save(tipoVehiculo);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
