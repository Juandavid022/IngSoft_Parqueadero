package com.mvcproject.v1.model;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Entity
@Table(name = "tipo_vehiculo_tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Tipo de Vehículo")
public class TipoVehiculoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID del tipo de vehículo", example = "1")
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    @Schema(description = "Descripción del tipo de vehículo", example = "Moto")
    private String descripcion;
}
