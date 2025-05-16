package com.mvcproject.v1.service.impl;

import com.mvcproject.v1.model.PagoModel;
import com.mvcproject.v1.repository.PagoRepository;
import com.mvcproject.v1.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Service
public class PagoServiceImpl implements PagoService {

    @Autowired
    private PagoRepository pagoRepository;
    

    @Override
    public List<PagoModel> findAll() {
        return pagoRepository.findAll();
    }

    @Override
    public Optional<PagoModel> findById(Long id) {
        return pagoRepository.findById(id);
    }

    @Override
    public PagoModel save(PagoModel pago) {
        return pagoRepository.save(pago);
    }

    @Override
    public void deleteById(Long id) {
        pagoRepository.deleteById(id);
    }

    public List<PagoModel> findPagosDeHoy() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(LocalTime.MAX);
        return pagoRepository.findByFechaPagoBetweenOrderByFechaPagoDesc(startOfDay, endOfDay);
    }


    @Override
    public Page<PagoModel> getPagosPaginados(Pageable pageable) {
        return pagoRepository.findAll(pageable);
    }

}
