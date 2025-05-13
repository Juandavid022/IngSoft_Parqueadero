package com.mvcproject.v1.controller;


import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.mvcproject.v1.model.VehiculoModel;
import com.mvcproject.v1.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



import com.mvcproject.v1.service.VehiculoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;




@Tag(name = "vehiculos", description = "Operaciones sobre el manejo y recursos de los vehiculos")
@RestController
@RequestMapping("/api/vehiculos")
@CrossOrigin(origins = "*")
public class VehiculoController {

      @Autowired
    private VehiculoRepository vehiculoRepository;

    // Obtener todos los vehículos
    @GetMapping
    public List<VehiculoModel> getAllVehiculos() {
        return vehiculoRepository.findAll();
    }

    // Obtener un vehículo por ID
    @GetMapping("/{id}")
    public ResponseEntity<VehiculoModel> getVehiculoById(@PathVariable Long id) {
        Optional<VehiculoModel> vehiculo = vehiculoRepository.findById(id);
        return vehiculo.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo vehículo
    @PostMapping
    public VehiculoModel createVehiculo(@RequestBody VehiculoModel vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    // Actualizar un vehículo existente
    @PutMapping("/{id}")
    public ResponseEntity<VehiculoModel> updateVehiculo(@PathVariable Long id, @RequestBody VehiculoModel vehiculoDetails) {
        Optional<VehiculoModel> vehiculoOptional = vehiculoRepository.findById(id);
        if (vehiculoOptional.isPresent()) {
            VehiculoModel vehiculo = vehiculoOptional.get();
            vehiculo.setPlaca(vehiculoDetails.getPlaca());
            vehiculo.setTipoid(vehiculoDetails.getTipoid());
            return ResponseEntity.ok(vehiculoRepository.save(vehiculo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un vehículo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehiculo(@PathVariable Long id) {
        if (vehiculoRepository.existsById(id)) {
            vehiculoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }







        

}




