package com.mvcproject.v1.model;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehicle_tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Modelo de un vehiculo")
public class VehiculoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "El ID único del vehiculo", example = "1")
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    @Schema(description = "Placa", example = "AAA001")
    private String placa;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipoid", referencedColumnName = "id")
    @Schema(description = "Tipo de vehículo ")
    private TipoVehiculoModel tipoid;

}
