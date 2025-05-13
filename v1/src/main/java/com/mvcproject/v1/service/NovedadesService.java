package com.mvcproject.v1.service;

import com.mvcproject.v1.model.NovedadModel;
import java.util.List;
import java.util.Optional;

public interface NovedadesService {
    List<NovedadModel> findAll();
    Optional<NovedadModel> findById(Long id);
    NovedadModel save(NovedadModel novedad);
    void deleteById(Long id);
}
