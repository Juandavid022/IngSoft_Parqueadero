package com.mvcproject.v1.repository;

import com.mvcproject.v1.model.PagoModel;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository<PagoModel, Long> {

    @Query("SELECT p FROM PagoModel p WHERE DATE(p.fechaPago) = CURRENT_DATE")
    List<PagoModel> findPagosDeHoy();

    List<PagoModel> findByFechaPagoBetweenOrderByFechaPagoDesc(LocalDateTime inicio, LocalDateTime fin);

}
