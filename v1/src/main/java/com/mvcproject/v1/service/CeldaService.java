package com.mvcproject.v1.service;

import com.mvcproject.v1.model.CeldaModel;
import java.util.List;
import java.util.Optional;

public interface CeldaService {
    List<CeldaModel> findAll();
    Optional<CeldaModel> findById(Long id);
    CeldaModel save(CeldaModel celda);
    void deleteById(Long id);
}
