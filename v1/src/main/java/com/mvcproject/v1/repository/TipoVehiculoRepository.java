package com.mvcproject.v1.repository;

import com.mvcproject.v1.model.TipoVehiculoModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoVehiculoRepository extends JpaRepository<TipoVehiculoModel, Long> {
    
}
