package com.mvcproject.v1.controller;

import com.mvcproject.v1.model.CeldaModel;
import com.mvcproject.v1.repository.CeldaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/celdas")
@CrossOrigin(origins = "*")
public class CeldaController {

    @Autowired
    private CeldaRepository celdaRepository;

    @GetMapping
    public List<CeldaModel> getAll() {
        return celdaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CeldaModel> getById(@PathVariable Long id) {
        return celdaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CeldaModel create(@RequestBody CeldaModel celda) {
        return celdaRepository.save(celda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CeldaModel> update(@PathVariable Long id, @RequestBody CeldaModel celdaDetails) {
        return celdaRepository.findById(id).map(celda -> {
            celda.setEstado(celdaDetails.getEstado());
            //celda.setPrecio(celdaDetails.getPrecio());
            celda.setTipoVehiculo(celdaDetails.getTipoVehiculo());
            return ResponseEntity.ok(celdaRepository.save(celda));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (celdaRepository.existsById(id)) {
            celdaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
