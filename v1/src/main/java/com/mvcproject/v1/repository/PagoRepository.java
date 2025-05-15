package com.mvcproject.v1.repository;

import com.mvcproject.v1.model.PagoModel;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository<PagoModel, Long> {
    List<PagoModel> findByFechaPagoBetween(LocalDateTime inicio, LocalDateTime fin);
    

}
