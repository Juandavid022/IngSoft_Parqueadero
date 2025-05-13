package com.mvcproject.v1.service.impl;

import com.mvcproject.v1.model.CeldaModel;
import com.mvcproject.v1.repository.CeldaRepository;
import com.mvcproject.v1.service.CeldaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CeldaServiceImpl implements CeldaService {

    @Autowired
    private CeldaRepository repository;

    @Override
    public List<CeldaModel> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<CeldaModel> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public CeldaModel save(CeldaModel celda) {
        return repository.save(celda);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
