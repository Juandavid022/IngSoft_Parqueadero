package com.mvcproject.v1.repository;

import com.mvcproject.v1.model.NovedadModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NovedadesRepository extends JpaRepository<NovedadModel, Long> {
}
