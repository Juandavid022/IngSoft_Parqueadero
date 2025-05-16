package com.mvcproject.v1.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import com.mvcproject.v1.model.PagoModel;

public interface PagoService {
    List<PagoModel> findAll();

    List<PagoModel> findPagosDeHoy();

    Optional<PagoModel> findById(Long id);

    PagoModel save(PagoModel pago);

    void deleteById(Long id);

    Page<PagoModel> getPagosPaginados(Pageable pageable);

}
