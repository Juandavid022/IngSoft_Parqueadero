package com.mvcproject.v1.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Column(nullable = false, unique = false, length = 50)
    private String registro;

    @Column(nullable = false, unique = false, length = 50)
    private String monto; 

    @Column(nullable = false, unique = false, length = 50)
    private String fechaPago;

//solo efectivo
}



    
