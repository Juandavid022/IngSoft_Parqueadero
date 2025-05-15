package com.mvcproject.v1.service.impl;

import com.mvcproject.v1.dto.CeldaConVehiculoDTO;
import com.mvcproject.v1.model.CeldaModel;
import com.mvcproject.v1.repository.CeldaRepository;
import com.mvcproject.v1.repository.RegistroRepository;
import com.mvcproject.v1.service.CeldaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CeldaServiceImpl implements CeldaService {

    @Autowired
    private CeldaRepository celdaRepository;

    @Autowired
    private RegistroRepository registroRepository;

    @Override
    public List<CeldaModel> findAll() {
        return celdaRepository.findAll();
    }

    @Override
    public Optional<CeldaModel> findById(Long id) {
        return celdaRepository.findById(id);
    }

    @Override
    public CeldaModel save(CeldaModel celda) {
        return celdaRepository.save(celda);
    }

    @Override
    public void deleteById(Long id) {
        celdaRepository.deleteById(id);
    }

        public List<CeldaConVehiculoDTO> obtenerCeldasConVehiculos() {
        List<CeldaModel> celdas = celdaRepository.findAll();
        List<CeldaConVehiculoDTO> resultado = new ArrayList<>();

        for (CeldaModel celda : celdas) {
            String placa = registroRepository
                    .findTopByCeldaAndFechaSalidaIsNullOrderByFechaEntradaDesc(celda)
                    .map(registro -> registro.getVehiculo().getPlaca())
                    .orElse(null);

            resultado.add(new CeldaConVehiculoDTO(celda, placa));
        }

        return resultado;
    }
}
