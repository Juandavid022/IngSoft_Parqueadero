package com.mvcproject.v1.repository;

import com.mvcproject.v1.model.RegistroModel;
import com.mvcproject.v1.model.VehiculoModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroRepository extends JpaRepository<RegistroModel, Long> {
    Optional<RegistroModel> findTopByVehiculoPlaca(String placa);
    Optional<RegistroModel> findByVehiculoAndFechaSalidaIsNull(VehiculoModel vehiculo);
    Optional<RegistroModel> findTopByVehiculo_PlacaOrderByFechaEntradaDesc(String placa);


}
