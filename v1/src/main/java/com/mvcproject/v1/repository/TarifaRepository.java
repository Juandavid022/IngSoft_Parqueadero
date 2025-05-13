package com.mvcproject.v1.repository;

import com.mvcproject.v1.model.TarifaModel;
import com.mvcproject.v1.model.TipoVehiculoModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarifaRepository extends JpaRepository<TarifaModel, Long> {
    TarifaModel findByTipoVehiculo(TipoVehiculoModel tipoVehiculo);
}
