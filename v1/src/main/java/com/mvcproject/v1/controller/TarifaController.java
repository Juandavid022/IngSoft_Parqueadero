package com.mvcproject.v1.controller;

import com.mvcproject.v1.model.TarifaModel;
import com.mvcproject.v1.repository.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarifas")
@CrossOrigin(origins = "*")
public class TarifaController {

    @Autowired
    private TarifaRepository tarifaRepository;

    @GetMapping
    public List<TarifaModel> getAll() {
        return tarifaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarifaModel> getById(@PathVariable Long id) {
        return tarifaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TarifaModel create(@RequestBody TarifaModel model) {
        return tarifaRepository.save(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarifaModel> update(@PathVariable Long id, @RequestBody TarifaModel details) {
        return tarifaRepository.findById(id).map(tarifa -> {
            tarifa.setTipoVehiculo(details.getTipoVehiculo());
            tarifa.setValorHora(details.getValorHora());
            return ResponseEntity.ok(tarifaRepository.save(tarifa));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (tarifaRepository.existsById(id)) {
            tarifaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
