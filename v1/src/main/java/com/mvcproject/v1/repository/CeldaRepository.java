package com.mvcproject.v1.repository;

import com.mvcproject.v1.model.CeldaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CeldaRepository extends JpaRepository<CeldaModel, Long> {
    List<CeldaModel> findByLibre(boolean libre);
    List<CeldaModel> findByTipoVehiculoIdAndLibreTrue(Long tipoVehiculoId);


    
}
