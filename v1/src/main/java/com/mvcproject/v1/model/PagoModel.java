package com.mvcproject.v1.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment_tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PagoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "registro_id", referencedColumnName = "id")
    private RegistroModel registro;

    @Column(nullable = false, unique = false, length = 50)
    private BigDecimal monto;

    @Column(nullable = false, unique = false, length = 50)
    private LocalDateTime fechaPago;

    // solo efectivo
}
