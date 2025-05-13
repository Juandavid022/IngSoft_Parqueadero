package com.mvcproject.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mvcproject.v1.model.VehiculoModel;
import java.util.Optional;


@Repository
public interface VehiculoRepository extends JpaRepository <VehiculoModel, Long> {
    Optional<VehiculoModel> findByPlaca(String placa);
 
    
}