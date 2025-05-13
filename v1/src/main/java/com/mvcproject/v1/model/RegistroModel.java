package com.mvcproject.v1.model;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "registro_tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Modelo de Registro de ingreso/salida de vehículos")
public class RegistroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del registro", example = "1")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id", referencedColumnName = "id")
    @Schema(description = "Vehículo que realiza el ingreso/salida")
    private VehiculoModel vehiculo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "celdaid", referencedColumnName = "id")
    @Schema(description = "Celda de parqueo asignada")
    private CeldaModel celda;

    @Column(name = "fecha_entrada", nullable = false)
    @Schema(description = "Fecha y hora de entrada", example = "2025-05-11T08:30:00")
    private LocalDateTime fechaEntrada;

    @Column(name = "fecha_salida")
    @Schema(description = "Fecha y hora de salida", example = "2025-05-11T10:45:00")
    private LocalDateTime fechaSalida;
}
