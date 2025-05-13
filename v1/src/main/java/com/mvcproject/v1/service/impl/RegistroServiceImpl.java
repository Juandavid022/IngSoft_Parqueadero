package com.mvcproject.v1.service.impl;

import com.mvcproject.v1.model.CeldaModel;
import com.mvcproject.v1.model.RegistroModel;
import com.mvcproject.v1.repository.CeldaRepository;
import com.mvcproject.v1.repository.RegistroRepository;
import com.mvcproject.v1.service.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RegistroServiceImpl implements RegistroService {


       @Autowired
    private RegistroRepository registroRepository;

    @Autowired
    private CeldaRepository celdaRepository;

    @Override
    public List<RegistroModel> findAll() {
        return registroRepository.findAll();
    }

    @Override
    public Optional<RegistroModel> findById(Long id) {
        return registroRepository.findById(id);
    }

    @Override
    public RegistroModel save(RegistroModel registro) {
        return registroRepository.save(registro);
    }

    @Override
    public void deleteById(Long id) {
        registroRepository.deleteById(id);
    }

    public RegistroModel registrarSalida(String placa) {
        Optional<RegistroModel> registroOpt = registroRepository
                .findTopByVehiculoPlaca(placa);

        if (registroOpt.isEmpty()) {
            throw new RuntimeException("No se encontró un registro activo para la placa: " + placa);
        }

        RegistroModel registro = registroOpt.get();
        registro.setFechaSalida(LocalDateTime.now());

        CeldaModel celda = registro.getCelda();
        celda.setLibre(true);
        celdaRepository.save(celda);

        return registroRepository.save(registro);
    }
}
