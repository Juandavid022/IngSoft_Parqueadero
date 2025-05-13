package com.mvcproject.v1.service;

import com.mvcproject.v1.model.TarifaModel;
import java.util.List;
import java.util.Optional;

public interface TarifaService {
    List<TarifaModel> findAll();
    Optional<TarifaModel> findById(Long id);
    TarifaModel save(TarifaModel tarifa);
    void deleteById(Long id);
}
