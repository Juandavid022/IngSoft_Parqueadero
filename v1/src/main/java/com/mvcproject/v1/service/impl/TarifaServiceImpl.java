package com.mvcproject.v1.service.impl;

import com.mvcproject.v1.model.TarifaModel;
import com.mvcproject.v1.repository.TarifaRepository;
import com.mvcproject.v1.service.TarifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarifaServiceImpl implements TarifaService {

    @Autowired
    private TarifaRepository repository;

    @Override
    public List<TarifaModel> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<TarifaModel> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public TarifaModel save(TarifaModel tarifa) {
        return repository.save(tarifa);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
