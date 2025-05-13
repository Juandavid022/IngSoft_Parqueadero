package com.mvcproject.v1.service.impl;

import com.mvcproject.v1.model.NovedadModel;
import com.mvcproject.v1.repository.NovedadesRepository;
import com.mvcproject.v1.service.NovedadesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NovedadesServiceImpl implements NovedadesService {

    @Autowired
    private NovedadesRepository repository;

    @Override
    public List<NovedadModel> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<NovedadModel> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public NovedadModel save(NovedadModel novedad) {
        return repository.save(novedad);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
