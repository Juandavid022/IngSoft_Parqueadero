package com.mvcproject.v1.model;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "novedades_tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Modelo de Novedades relacionadas con el parqueo de vehículos")
public class NovedadModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la novedad", example = "1")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "placaid", referencedColumnName = "id")
    @Schema(description = "Vehículo asociado a la novedad")
    private VehiculoModel placaid;

    @ManyToOne(optional = false)
    @JoinColumn(name = "celdaid", referencedColumnName = "id")
    @Schema(description = "Celda de parqueo asociada a la novedad")
    private CeldaModel celdaid;

    @Column(nullable = false)
    @Schema(description = "Fecha de la novedad", example = "2025-05-11T10:30:00")
    private LocalDateTime fecha;

    @Column(nullable = false, length = 255)
    @Schema(description = "Descripción de la novedad", example = "Novedad de cambio de celda de parqueo debido a mantenimiento.")
    private String descripcion;
}
