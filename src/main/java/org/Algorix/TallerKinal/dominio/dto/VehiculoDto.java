package org.Algorix.TallerKinal.dominio.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.Algorix.TallerKinal.dominio.MarcaVehiculo;

public record VehiculoDto (
        Long id_vehiculo,
        Long idCliente,

        @NotBlank(message = "Las placas no pueden estar vacias")
        String licensePlate,

        @NotBlank(message = "El color no puede estar vacio")
        String color,

        @NotBlank(message = "El modelo no puede estar vacio")
        String model,

        @NotNull(message = "La marca no puede estar vacia y tiene  ")
        MarcaVehiculo marca,

        @Min(value = 1886, message = "El año no puede ser menor MechanicView 1886")
        @Max(value = 2100, message = "El año no puede ser mayor MechanicView 2100")
        Integer year
){
    // Getters JavaBean para compatibilidad con EL/JSF (camelCase y variantes con guión bajo)
    public Long getIdVehiculo() { return id_vehiculo; }
    public Long getId_vehiculo() { return id_vehiculo; }

    public Long getIdCliente() { return idCliente; }
    public Long getId_cliente() { return idCliente; }

    public String getLicensePlate() { return licensePlate; }
    public String getPlacas() { return licensePlate; }

    public String getColor() { return color; }

    public String getModel() { return model; }
    public String getModelo() { return model; }

    public MarcaVehiculo getMarca() { return marca; }

    public Integer getYear() { return year; }
    public Integer getAnio() { return year; }
}
