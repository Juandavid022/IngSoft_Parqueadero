package com.mvcproject.v1.controller;

import com.mvcproject.v1.model.TipoVehiculoModel;
import com.mvcproject.v1.repository.TipoVehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipo-vehiculos")
@CrossOrigin(origins = "*")
public class TipoVehiculoController {

    @Autowired
    private TipoVehiculoRepository tipoVehiculoRepository;

    @GetMapping
    public List<TipoVehiculoModel> getAll() {
        return tipoVehiculoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoVehiculoModel> getById(@PathVariable Long id) {
        return tipoVehiculoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TipoVehiculoModel create(@RequestBody TipoVehiculoModel model) {
        return tipoVehiculoRepository.save(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoVehiculoModel> update(@PathVariable Long id, @RequestBody TipoVehiculoModel details) {
        return tipoVehiculoRepository.findById(id).map(existing -> {
            existing.setDescripcion(details.getDescripcion());
            return ResponseEntity.ok(tipoVehiculoRepository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (tipoVehiculoRepository.existsById(id)) {
            tipoVehiculoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
