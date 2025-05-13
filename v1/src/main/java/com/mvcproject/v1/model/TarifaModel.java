package com.mvcproject.v1.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Entity
@Table(name = "tarifa_tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Modelo de Tarifa")
public class TarifaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la tarifa", example = "1")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tipo_vehiculo_id")
    private TipoVehiculoModel tipoVehiculo;

    @Column(name = "valor_hora")
    private BigDecimal valorHora;
}
