package com.mvcproject.v1.dto;

import com.mvcproject.v1.model.CeldaModel;

public class CeldaConVehiculoDTO {

    private CeldaModel celda;
    private String placaVehiculo;

    // Constructor con CeldaModel y String
    public CeldaConVehiculoDTO(CeldaModel celda, String placaVehiculo) {
        this.celda = celda;
        this.placaVehiculo = placaVehiculo;
    }

    // getters y setters
    public CeldaModel getCelda() {
        return celda;
    }

    public void setCelda(CeldaModel celda) {
        this.celda = celda;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }
}

