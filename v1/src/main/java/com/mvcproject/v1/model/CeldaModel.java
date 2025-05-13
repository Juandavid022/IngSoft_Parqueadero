package com.mvcproject.v1.model;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Entity
@Table(name = "celda_tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Modelo de Celda de parqueo")
public class CeldaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la celda", example = "1")
    private Long id;

    @Column(nullable = false, length = 20)
    @Schema(description = "Estado de la celda", example = "Disponible")
    private String estado;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_vehiculo_id", referencedColumnName = "id")
    @Schema(description = "Tipo de vehículo permitido en esta celda")
    private TipoVehiculoModel tipoVehiculo;

    @Column(nullable = false, length = 20)
    @Schema(description = "Estado de la celda", example = "Disponible")
    private boolean libre;
}
