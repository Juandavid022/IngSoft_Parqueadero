package com.mvcproject.v1.service.impl;

import com.mvcproject.v1.model.VehiculoModel;
import com.mvcproject.v1.repository.VehiculoRepository;
import com.mvcproject.v1.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    @Autowired
    private VehiculoRepository repository;

    @Override
    public List<VehiculoModel> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<VehiculoModel> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public VehiculoModel save(VehiculoModel vehiculo) {
        return repository.save(vehiculo);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
