package com.mvcproject.v1.service;

import java.util.List;
import java.util.Optional;

import com.mvcproject.v1.model.PagoModel;


public interface PagoService {
    List<PagoModel> findAll();
    List<PagoModel> findPagosDeHoy();
    Optional<PagoModel> findById(Long id);
    PagoModel save(PagoModel pago);
    void deleteById(Long id);
}
